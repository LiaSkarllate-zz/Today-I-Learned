/*
    Criação de uma stored procedure que recebe por parâmetro todas as colunas da tabela MOVIMENTOS,
    exceto duas, a saber: (i) NRO_MOVIMENTO; e (ii) DATA_MOVIMENTO. Esse procedimento salva um registro
    de movimento com os dados recebidos por parâmetro, desde que a conta passada exista na tabela CONTAS. 
    Para tal, compara-se todas as colunas que compõem a chava primária da tabela CONTAS). Ao salvar, é 
    usada a data corrente para a coluna DATA_MOVIMENTO. Por fim, o procedimento é executado.
*/
CREATE PROCEDURE P_SALVAR_MOVIMENTO
(@COD_COLIGADA  VARCHAR(3),
 @COD_AGENCIA   VARCHAR(5),
 @NRO_CONTA     VARCHAR(7),
 @VALOR         NUMERIC(17, 2),
 @MORA          NUMERIC(17, 2),
 @MULTA         NUMERIC(17, 2),
 @DESCONTO      NUMERIC(17, 2),
 @DESCRICAO     VARCHAR(100))
AS
BEGIN 
    IF((SELECT  COD_COLIGADA 
        FROM    CONTAS 
        WHERE   CONTAS.COD_COLIGADA = @COD_COLIGADA AND
                CONTAS.COD_AGENCIA  = @COD_AGENCIA  AND
                CONTAS.NRO_CONTA    = @NRO_CONTA)   IS NOT NULL)
        BEGIN
            INSERT INTO MOVIMENTOS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, DATA_MOVIMENTO, VALOR, MORA, MULTA, DESCONTO, DESCRICAO)
            VALUES                 (@COD_COLIGADA, @COD_AGENCIA, @NRO_CONTA, GETDATE(), @VALOR, @MORA, @MULTA, @DESCONTO, @DESCRICAO)
        
            SELECT 'A operação executou com êxito.' AS MENSAGEM
        END
    ELSE
        BEGIN
            SELECT 'A operação falhou em executar.' AS MENSAGEM
        END
END

--Inserção de um movimento por meio da stored procedure recém-criada:
EXEC P_SALVAR_MOVIMENTO '001', '00001', '1111111', 150, 0, 0, 0, "Depósito"	

--Consulta que retorna o registro recém-criado:
SELECT TOP 1    *
FROM            MOVIMENTOS
ORDER BY        NRO_MOVIMENTO DESC

--Inserção de um movimento por meio da stored procedure recém-criada, mas para outra conta:
EXEC P_SALVAR_MOVIMENTO '002', '00001', '2222222', 550, 0, 0, 0, "Transferência"	

--Alteração da tabela MOVIMENTOS de modo a incluir verificações nas colunas VALOR, MORA, MULTA e DESCONTO:
ALTER TABLE MOVIMENTOS ADD CHECK (VALOR >= 0)
ALTER TABLE MOVIMENTOS ADD CHECK (MORA >= 0)
ALTER TABLE MOVIMENTOS ADD CHECK (MULTA >= 0)
ALTER TABLE MOVIMENTOS ADD CHECK (DESCONTO >= 0)

--Consulta que retorna o menor, o maior e a média da coluna VALOR da tabela MOVIMENTOS:
SELECT  MIN(VALOR) AS MÍNIMO, 
        MAX(VALOR) AS MÁXIMO, 
        AVG(VALOR) AS MÉDIA
FROM    MOVIMENTOS

/*
    Consulta que retorna as colunas CPF_CNPJ e NOME da tabela PESSOA, além da coluna DATA_MOVIMENTO 
    da tabela MOVIMENTOS, desde que seja o maior valor, que a conta seja do tipo poupança e que a 
    pessoa seja cliente. 
*/
SELECT      PESSOAS.CPF_CNPJ, PESSOAS.NOME, MOVIMENTOS.DATA_MOVIMENTO
FROM        PESSOAS
INNER JOIN  CONTAS
ON          CONTAS.COD_PESSOA = PESSOAS.COD_PESSOA
INNER JOIN  MOVIMENTOS
ON          CONTAS.COD_COLIGADA = MOVIMENTOS.COD_COLIGADA AND
            CONTAS.COD_AGENCIA  = MOVIMENTOS.COD_AGENCIA  AND
            CONTAS.NRO_CONTA    = MOVIMENTOS.NRO_CONTA
WHERE       CONTAS.IND_POUPANCA       = 'S' AND
            PESSOAS.IND_CLIENTE_BANCO = 'S' AND
            MOVIMENTOS.VALOR    = (SELECT   MAX(VALOR) 
                                   FROM     MOVIMENTOS)