/*
    Criação de uma trigger que, ao salvar uma conta na tabela CONTAS, grava as 
    colunas USUARIO_INCLUSAO e DATA_INCLUSAO como a data corrente com um nome e a data corrente, respectivamente.
*/
CREATE TRIGGER TRG_SALVAR_CONTA ON CONTAS FOR INSERT
AS
BEGIN
    UPDATE      CONTAS
    SET         USUARIO_INCLUSAO = 'Bill Gates',
                DATA_INCLUSAO    = GETDATE()
    FROM        CONTAS
    INNER JOIN  INSERTED
    ON          CONTAS.COD_COLIGADA = INSERTED.COD_COLIGADA AND
                CONTAS.COD_AGENCIA  = INSERTED.COD_AGENCIA  AND
                CONTAS.NRO_CONTA    = INSERTED.NRO_CONTA
END

--Inserção de uma conta para gatilhar a trigger recém-criada:
INSERT INTO CONTAS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, COD_PESSOA, IND_POUPANCA, USUARIO_INCLUSAO, DATA_INCLUSAO, USUARIO_ALTERACAO, DATA_ALTERACAO)
VALUES             ('003', '00001', '3333333', '001', 'N', NULL, NULL, NULL, NULL)

--Remoção da trigger recém-criada:
DROP TRIGGER TRG_SALVAR_CONTA

/*
    Criação de uma trigger que, ao alterar uma conta na tabela CONTAS, grava as
    colunas USUARIO_ALTERACAO e DATA_ALTERACAO com um nome e a data corrente, respectivamente.
*/
CREATE TRIGGER TRG_ALTERAR_CONTA ON CONTAS FOR UPDATE
AS
BEGIN
    UPDATE      CONTAS
    SET         USUARIO_ALTERACAO = 'Tim Cook',
                DATA_ALTERACAO    = GETDATE()
    FROM        CONTAS
    INNER JOIN  INSERTED
    ON          CONTAS.COD_COLIGADA = INSERTED.COD_COLIGADA AND
                CONTAS.COD_AGENCIA  = INSERTED.COD_AGENCIA  AND
                CONTAS.NRO_CONTA    = INSERTED.NRO_CONTA
END

--Alteração de uma conta para gatilhar a trigger recém-criada:
UPDATE  CONTAS
SET     IND_POUPANCA = 'S'
WHERE   COD_COLIGADA = '003'    AND
        COD_AGENCIA  = '00001'  AND
        NRO_CONTA    = '3333333'

--Remoção da trigger recém-criada:
DROP TRIGGER TRG_ALTERAR_CONTA

--Consulta que retorna a soma de todos os movimentos feitos dentro de um determinado período:
SELECT  SUM(MOVIMENTOS.VALOR) AS SOMA
FROM    MOVIMENTOS
WHERE   MOVIMENTOS.DATA_MOVIMENTO BETWEEN '2000-07-31' AND '2021-12-01'

--Inserção de uma pessoa sem conta e sem movimentação:
INSERT INTO PESSOAS (COD_PESSOA, CPF_CNPJ, NOME, NASCIMENTO, TIPO_PESSOA, NUMERO_FILHOS, IND_CLIENTE_BANCO, TIPO_LOGRADOURO, LOGRADOURO, BAIRRO, CIDADE, UF, PAIS, CEP, OBVERVACAO)
VALUES              ('004', '44444444444', 'Jon Bon Jovi', '1962-01-01', 'F', 2, 'S', 'Casa', 'E Lake Avenue, 940 - YH', 'Ybor Heights', 'Tampa', 'FL', 'EUA', '44444444', NULL)

--Consulta que retorna os movimentos que foram realizados para clientes cujo nome começa com a letra B:
SELECT      PESSOAS.NOME, MOVIMENTOS.NRO_MOVIMENTO, MOVIMENTOS.DATA_MOVIMENTO, MOVIMENTOS.VALOR
FROM        PESSOAS
INNER JOIN  CONTAS
ON          CONTAS.COD_PESSOA = PESSOAS.COD_PESSOA
INNER JOIN  MOVIMENTOS
ON          CONTAS.COD_COLIGADA = MOVIMENTOS.COD_COLIGADA AND
            CONTAS.COD_AGENCIA  = MOVIMENTOS.COD_AGENCIA  AND
            CONTAS.NRO_CONTA    = MOVIMENTOS.NRO_CONTA
WHERE       PESSOAS.NOME LIKE 'B%'

--Consulta que retorna os clientes que possuem contas que não possuem movimentos:
SELECT      PESSOAS.NOME, PESSOAS.COD_PESSOA, PESSOA.CPF_CNPJ
FROM        PESSOAS
INNER JOIN  CONTAS
ON          CONTAS.COD_PESSOA = PESSOAS.COD_PESSOA
LEFT JOIN   MOVIMENTOS
ON          CONTAS.COD_COLIGADA = MOVIMENTOS.COD_COLIGADA AND
            CONTAS.COD_AGENCIA  = MOVIMENTOS.COD_AGENCIA  AND
            CONTAS.NRO_CONTA    = MOVIMENTOS.NRO_CONTA
WHERE       MOVIMENTOS.COD_COLIGADA IS NULL

--Criação de uma stored procedure que utiliza os recursos de transação:
CREATE PROCEDURE P_SALVAR_MOVIMENTO_TRAN
(@COD_COLIGADA  VARCHAR(3),
 @COD_AGENCIA   VARCHAR(5),
 @NRO_CONTA     VARCHAR(7),
 @VALOR         NUMERIC(17, 2),
 @MORA          NUMERIC(17, 2),
 @MULTA         NUMERIC(17, 2),
 @DESCONTO      NUMERIC(17, 2),
 @DESCRICAO     VARCHAR(100))
AS
BEGIN TRAN
    IF((SELECT  COD_COLIGADA 
        FROM    CONTAS 
        WHERE   CONTAS.COD_COLIGADA = @COD_COLIGADA AND
                CONTAS.COD_AGENCIA  = @COD_AGENCIA  AND
                CONTAS.NRO_CONTA    = @NRO_CONTA)   IS NOT NULL)
        BEGIN
            INSERT INTO MOVIMENTOS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, DATA_MOVIMENTO, VALOR, MORA, MULTA, DESCONTO, DESCRICAO)
            VALUES                 (@COD_COLIGADA, @COD_AGENCIA, @NRO_CONTA, GETDATE(), @VALOR, @MORA, @MULTA, @DESCONTO, @DESCRICAO)
        
            SELECT 'A operação executou com êxito.' AS MENSAGEM
            COMMIT TRAN
        END
    ELSE
        BEGIN
            SELECT 'A operação falhou em executar.' AS MENSAGEM
            ROLLBACK TRAN
        END

--Criação de uma view que retorna os movimentos e números das contas associadas os quais têm valores maior do que 1000:
CREATE  VIEW VW_CONTAS_MOVIMENTOS_MAIOR_1000 AS
SELECT  MOVIMENTOS.NRO_CONTA
FROM    MOVIMENTOS
WHERE   MOVIMENTOS.VALOR > 1000

--Consulta a partir da view recém-criada:
SELECT  COUNT(NRO_CONTA) AS QUANTIDADE
FROM    VW_CONTAS_MOVIMENTOS_MAIOR_1000

/*
    Criação de uma stored procedure com um cursor que lê a tabela MOVIMENTOS, a percorre e exibe
    'movimento alto' para movimentos com VALOR maior que 1000 e 'movimento baixo' para os demais.
*/
CREATE PROCEDURE P_CATEGORIZA_MOVIMENTOS
AS
DECLARE @COD_COLIGADA   VARCHAR(3),
        @COD_AGENCIA    VARCHAR(5),
        @NRO_CONTA      VARCHAR(7),
        @DATA_MOVIMENTO DATETIME,
        @VALOR          NUMERIC(17, 2)
BEGIN
    DECLARE CURSOR_MOVIMENTOS CURSOR FOR
    SELECT  MOVIMENTOS.COD_COLIGADA, 
            MOVIMENTOS.COD_AGENCIA, 
            MOVIMENTOS.NRO_CONTA,
            MOVIMENTOS.DATA_MOVIMENTO, 
            MOVIMENTOS.VALOR
    FROM    MOVIMENTOS
    
    OPEN CURSOR_MOVIMENTOS

    FETCH NEXT FROM CURSOR_MOVIMENTOS INTO @COD_COLIGADA, @COD_AGENCIA, @NRO_CONTA, @DATA_MOVIMENTO, @VALOR
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF @VALOR > 1000
            SELECT 'A conta de número' + @NRO_CONTA + 'teve um movimento alto (R$' + CONVERT(VARCHAR(20), @VALOR) + ').'
        ELSE
            SELECT 'A conta de número' + @NRO_CONTA + 'teve um movimento baixo (R$' + CONVERT(VARCHAR(20), @VALOR) + ').'
    
        FETCH NEXT FROM CURSOR_MOVIMENTOS INTO @COD_COLIGADA, @COD_AGENCIA, @NRO_CONTA, @DATA_MOVIMENTO, @VALOR
    END

    CLOSE       CURSOR_MOVIMENTOS
    DEALLOCATE  CURSOR_MOVIMENTOS
END

/*
    Criação de uma trigger que, ao alterar um movimento na tabela MOVIMENTOS, verfica se a coluna DATA_MOVIMENTO
    foi alterada e, se sim, exibe uma mensagem de erro caso a data do movimento seja menor que a data atual.
*/
CREATE TRIGGER TRG_ALTERAR_MOVIMENTO ON MOVIMENTOS FOR UPDATE
AS
DECLARE @EXISTE BIT = 0
BEGIN
    SELECT      @EXISTE = 1
    FROM        INSERTED
    INNER JOIN  DELETED
    ON          INSERTED.COD_COLIGADA  = DELETED.COD_COLIGADA AND
                INSERTED.COD_AGENCIA   = DELETED.COD_AGENCIA  AND
                INSERTED.NRO_CONTA     = DELETED.NRO_CONTA    AND
                INSERTED.NRO_MOVIMENTO = DELETED.NRO_MOVIMENTO
    WHERE       INSERTED.DATA_MOVIMENTO <> DELETED.DATA_MOVIMENTO AND
                INSERTED.DATA_MOVIMENTO < GETDATE()

    IF @EXISTE = 1
        BEGIN
            SELECT 'A operação executou com êxito.' AS MENSAGEM
        END
    ELSE
        BEGIN
            SELECT 'A operação falhou em executar.' AS MENSAGEM
        END
END

--Alteração de um movimento para gatilhar a trigger recém-criada:
UPDATE  MOVIMENTOS
SET     DATA_MOVIMENTO = DATEADD(DD, -1, GETDATE())
WHERE   COD_COLIGADA  = '001'        AND
        COD_AGENCIA   = '00001'      AND
        NRO_CONTA     = '1111111'    AND
        NRO_MOVIMENTO = 1

--Remoção da trigger recém-criada:
DROP TRIGGER TRG_ALTERAR_MOVIMENTO

/*
    Criação de uma função que calcula o saldo anterior de um dado movimento, que é soma de todos os movimentos
    cuja data é menor que a data desse dado movimento, e que apresenta os movimentos das contas com a data, valor, saldo anterior e
    saldo atual (valor do movimento somado ao saldo anterior).
*/
CREATE FUNCTION F_MOVIMENTOS_SALDO_ANTERIOR
(@COD_COLIGADA      VARCHAR(3),
 @COD_AGENCIA       VARCHAR(5),
 @NRO_CONTA         VARCHAR(7),
 @DATA_REFERENCIA   DATETIME)

RETURNS NUMERIC(20, 2) 
WITH EXECUTE AS CALLER
AS
BEGIN
    DECLARE @SALDO_ANTERIOR NUMERIC(20, 2) = (
        SELECT      SUM(MOVIMENTOS.VALOR)
        FROM        MOVIMENTOS
        INNER JOIN  CONTAS
        ON          CONTAS.COD_COLIGADA = MOVIMENTOS.COD_COLIGADA AND
                    CONTAS.COD_AGENCIA  = MOVIMENTOS.COD_AGENCIA  AND
                    CONTAS.NRO_CONTA    = MOVIMENTOS.NRO_CONTA
        WHERE       MOVIMENTOS.COD_COLIGADA = @COD_COLIGADA     AND
                    MOVIMENTOS.COD_AGENCIA = @COD_AGENCIA       AND
                    MOVIMENTOS.NRO_CONTA = @NRO_CONTA           AND
                    MOVIMENTOS.DATA_MOVIMENTO < @DATA_REFERENCIA
        GROUP BY    MOVIMENTOS.COD_COLIGADA, MOVIMENTOS.COD_AGENCIA, MOVIMENTOS.NRO_CONTA
    )

    IF @SALDO_ANTERIOR IS NULL SET @SALDO_ANTERIOR = 0.00

    RETURN @SALDO_ANTERIOR
END

--Uso da função recém-criada:
SELECT      MOVIMENTOS.COD_COLIGADA,
            MOVIMENTOS.COD_AGENCIA,
            MOVIMENTOS.NRO_CONTA,
            MOVIMENTOS.NRO_MOVIMENTO,
            MOVIMENTOS.DATA_MOVIMENTO,
            MOVIMENTOS.VALOR,
            DBO.F_MOVIMENTOS_SALDO_ANTERIOR(MOVIMENTOS.COD_COLIGADA, MOVIMENTOS.COD_AGENCIA, MOVIMENTOS.NRO_CONTA, MOVIMENTOS.DATA_MOVIMENTO) AS SALDO_ANTERIOR,
            (MOVIMENTOS.VALOR + DBO.F_MOVIMENTOS_SALDO_ANTERIOR(MOVIMENTOS.COD_COLIGADA, MOVIMENTOS.COD_AGENCIA, MOVIMENTOS.NRO_CONTA, MOVIMENTOS.DATA_MOVIMENTO)) AS SALDO_ATUAL
FROM        MOVIMENTOS
ORDER BY    MOVIMENTOS.COD_COLIGADA, MOVIMENTOS.COD_AGENCIA, MOVIMENTOS.NRO_CONTA, MOVIMENTOS.DATA_MOVIMENTO

--Exercícios Opcionais:

/*
    Criação de uma stored procedure que soma 10 na coluna DESCONTO e subtrai 10 da VALOR da tabela MOVIMENTOS
    para todas as pessoas que cumprirem dois requisitos, a saber: (i) ter uma conta poupança; e (ii) o dia e o
    mês do campo NASCIMENTO da pessoa ser igual ao dia e ao mês do campo DATA_MOVIMENTO da tabela MOVIMENTO;
*/
CREATE PROCEDURE P_APLICA_PROMOCAO
AS
BEGIN
    UPDATE      MOVIMENTOS
    SET         DESCONTO = DESCONTO + 10,
                VALOR = VALOR - 10
    FROM        MOVIMENTOS
    INNER JOIN  CONTAS
    ON          MOVIMENTOS.COD_COLIGADA = CONTAS.COD_COLIGADA AND
                MOVIMENTOS.COD_AGENCIA  = CONTAS.COD_AGENCIA  AND
                MOVIMENTOS.NRO_CONTA    = CONTAS.NRO_CONTA   
    INNER JOIN  PESSOAS
    ON          CONTAS.COD_PESSOA   = PESSOAS.COD_PESSOA
    WHERE       CONTAS.IND_POUPANCA = 'S'                                                       AND
                DATEPART(day, PESSOAS.NASCIMENTO)   = DATEPART(day, MOVIMENTOS.DATA_MOVIMENTO)  AND
                DATEPART(month, PESSOAS.NASCIMENTO) = DATEPART(month, MOVIMENTOS.DATA_MOVIMENTO)
END

--Inserção de uma pessoa com data de nascimento 05/01/1990:
INSERT INTO PESSOAS (COD_PESSOA, CPF_CNPJ, NOME, NASCIMENTO, TIPO_PESSOA, NUMERO_FILHOS, IND_CLIENTE_BANCO, TIPO_LOGRADOURO, LOGRADOURO, BAIRRO, CIDADE, UF, PAIS, CEP, OBVERVACAO)
VALUES              ('005', '55555555555', 'Bill Gates', '1990-01-05', 'F', DEFAULT, 'S', 'Casa', '10th Avenue, 800 - Manhattan', 'Manhattan', 'New York', 'NY', 'EUA', '55555555', 'Escritor')

--Inserção de uma conta para a pessoa recém-criada:
INSERT INTO CONTAS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, COD_PESSOA, IND_POUPANCA, USUARIO_INCLUSAO, DATA_INCLUSAO, USUARIO_ALTERACAO, DATA_ALTERACAO)
VALUES             ('005', '00001', '5555555', '005', 'S', 'Alan Wake', '2021-10-28', NULL, NULL)

--Inserção de um movimento para a conta recém-criada com data 05/01/2010:
INSERT INTO MOVIMENTOS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, DATA_MOVIMENTO, VALOR, MORA, MULTA, DESCONTO, DESCRICAO)
            VALUES     ('005', '00001', '5555555', '2010-01-05', 100, 0, 0, 0, 'Pix')

--Inserção de um movimento para a conta recém-criada com data 07/01/2010:
INSERT INTO MOVIMENTOS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, DATA_MOVIMENTO, VALOR, MORA, MULTA, DESCONTO, DESCRICAO)
            VALUES     ('005', '00001', '5555555', '2010-01-07', 100, 0, 0, 0, 'Pix')

--Inserção de um movimento para a conta recém-criada:
INSERT INTO MOVIMENTOS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, DATA_MOVIMENTO, VALOR, MORA, MULTA, DESCONTO, DESCRICAO)
VALUES                 ('100', '0085', '8584667', '2021-01-07', 100, 0, 0, 0, NULL)

--Execução da stored procedure recém-criada:
EXEC P_APLICA_PROMOCAO

--Consulta que retorna as contas que possuem movimentos com desconto:
SELECT      CONTAS.COD_COLIGADA, CONTAS.COD_AGENCIA, CONTAS.NRO_CONTA
FROM        MOVIMENTOS
INNER JOIN  CONTAS
ON          CONTAS.COD_COLIGADA = MOVIMENTOS.COD_COLIGADA AND
            CONTAS.COD_AGENCIA  = MOVIMENTOS.COD_AGENCIA  AND
            CONTAS.NRO_CONTA    = MOVIMENTOS.NRO_CONTA
WHERE       MOVIMENTOS.DESCONTO <> 0
GROUP BY    CONTAS.COD_COLIGADA, CONTAS.COD_AGENCIA, CONTAS.NRO_CONTA

--Consulta que retorna o ano de cada registro da tabela movimentos:
SELECT  DATEPART(year, MOVIMENTOS.DATA_MOVIMENTO) AS YEAR
FROM    MOVIMENTOS

--Consulta que retorna "Sim" para contas poupança e "Não" para outras desde que a pessoa da conta seja cliente do banco:
SELECT  CONTAS.COD_COLIGADA, CONTAS.COD_AGENCIA, CONTAS.NRO_CONTA, 
    CASE
        WHEN    CONTAS.IND_POUPANCA = 'S' THEN 'Sim'
        ELSE    'Não'
    END AS POUPANCA
FROM        CONTAS
INNER JOIN  PESSOAS
ON          CONTAS.COD_PESSOA = PESSOAS.COD_PESSOA
WHERE       PESSOAS.IND_CLIENTE_BANCO = 'S'

--Consulta que usa a instrução LEFT JOIN de modo a retornar as contas que não possuem movimentos.
SELECT      CONTAS.COD_COLIGADA, CONTAS.COD_AGENCIA, CONTAS.NRO_CONTA
FROM        CONTAS
LEFT JOIN   MOVIMENTOS
ON          CONTAS.COD_COLIGADA = MOVIMENTOS.COD_COLIGADA AND
            CONTAS.COD_AGENCIA  = MOVIMENTOS.COD_AGENCIA  AND
            CONTAS.NRO_CONTA    = MOVIMENTOS.NRO_CONTA
WHERE       MOVIMENTOS.COD_COLIGADA IS NULL

--Consulta que usa a instrução RIGHT JOIN de modo a retornar as pessoas que não possuem contas:
SELECT      PESSOAS.NOME, PESSOAS.COD_PESSOA
FROM        CONTAS
RIGHT JOIN  PESSOAS
ON          PESSOAS.COD_PESSOA = CONTAS.COD_PESSOA
WHERE       CONTAS.COD_COLIGADA IS NULL

--Consulta que usa a instrução INNER JOIN de modo a retornar as pessoas e suas respectivas constas:
SELECT      PESSOAS.NOME, PESSOAS.COD_PESSOA, CONTAS.COD_COLIGADA, CONTAS.COD_AGENCIA, CONTAS.NRO_CONTA
FROM        CONTAS
INNER JOIN  PESSOAS
ON          PESSOAS.COD_PESSOA = CONTAS.COD_PESSOA

--Consulta que retorna as colunas VALOR, MORA, MULTA da tabela MOVIMENTOS com a garantia de retorna 0 se for NULL:
SELECT  ISNULL(MOVIMENTOS.VALOR, 0)     AS VALOR, 
        ISNULL(MOVIMENTOS.MORA, 0)      AS MORA,
        ISNULL(MOVIMENTOS.MULTA, 0)     AS MULTA
FROM    MOVIMENTOS