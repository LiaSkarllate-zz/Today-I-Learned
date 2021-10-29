--Criação de um banco de dados chamado AB_TREINAMENTO_WILLIAM_FERNANDES_DORANTE:
CREATE DATABASE AB_TREINAMENTO_WILLIAM_FERNANDES_DORANTE

--Configuração para usar o banco de dados recém-criado:
USE AB_TREINAMENTO_WILLIAM_FERNANDES_DORANTE

--Criação da tabela PESSOAS:
CREATE TABLE PESSOAS(
    COD_PESSOA          VARCHAR(3)      PRIMARY KEY,
    CPF_CNPJ            VARCHAR(14)     NOT NULL,
    NOME                VARCHAR(100)    NOT NULL,
    NASCIMENTO          DATE            NOT NULL,
    TIPO_PESSOA         VARCHAR(1)      NOT NULL CHECK (TIPO_PESSOA = 'F' OR TIPO_PESSOA = 'J'),
    NUMERO_FILHOS       INT             NOT NULL DEFAULT 0,
    IND_CLIENTE_BANCO   VARCHAR(1)      NOT NULL CHECK (IND_CLIENTE_BANCO = 'S' OR IND_CLIENTE_BANCO = 'N'),
    TIPO_LOGRADOURO     VARCHAR(10),
    LOGRADOURO          VARCHAR(100),
    BAIRRO              VARCHAR(50),
    CIDADE              VARCHAR(50),
    UF                  VARCHAR(2),
    PAIS                VARCHAR(50),
    CEP                 VARCHAR(8),
    OBVERVACAO          VARCHAR(100)
)

--Criação da tabela CONTAS:
CREATE TABLE CONTAS(
    COD_COLIGADA        VARCHAR(3),
    COD_AGENCIA         VARCHAR(5),
    NRO_CONTA           VARCHAR(7),
    COD_PESSOA          VARCHAR(3)      NOT NULL FOREIGN KEY REFERENCES PESSOAS(COD_PESSOA),
    IND_POUPANCA        VARCHAR(1)      NOT NULL CHECK (IND_POUPANCA = 'S' OR IND_POUPANCA = 'N'),
    USUARIO_INCLUSAO    VARCHAR(100),
    DATA_INCLUSAO       DATE,
    USUARIO_ALTERACAO   VARCHAR(100),
    DATA_ALTERACAO      DATE

    CONSTRAINT PK_CONTA PRIMARY KEY (COD_COLIGADA, COD_AGENCIA, NRO_CONTA)
)

--Criação da tabela MOVIMENTOS:
CREATE TABLE MOVIMENTOS(
    COD_COLIGADA    VARCHAR(3),
    COD_AGENCIA     VARCHAR(5),
    NRO_CONTA       VARCHAR(7),
    NRO_MOVIMENTO   INT             IDENTITY(1,1),
    DATA_MOVIMENTO  DATE            NOT NULL,
    VALOR           NUMERIC(17, 2),
    MORA            NUMERIC(17, 2),
    MULTA           NUMERIC(17, 2),
    DESCONTO        NUMERIC(17, 2),
    DESCRICAO       VARCHAR(100),

    CONSTRAINT PK_MOVIMENTO PRIMARY KEY (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, NRO_MOVIMENTO),
    CONSTRAINT FK_CONTA     FOREIGN KEY (COD_COLIGADA, COD_AGENCIA, NRO_CONTA) REFERENCES CONTAS(COD_COLIGADA, COD_AGENCIA, NRO_CONTA)
)

--Inserção de uma pessoa física que foi indicada:
INSERT INTO PESSOAS (COD_PESSOA, CPF_CNPJ, NOME, NASCIMENTO, TIPO_PESSOA, NUMERO_FILHOS, IND_CLIENTE_BANCO, TIPO_LOGRADOURO, LOGRADOURO, BAIRRO, CIDADE, UF, PAIS, CEP, OBVERVACAO)
VALUES              ('001', '11111111111', 'William Fernandes Dorante', '2000-01-01', 'F', DEFAULT, 'S', 'Casa', 'Rua 19, 8456 - CJ', 'Cidade Jardim', 'Rio Claro', 'SP', 'Brasil', '11111111', NULL)

--Inserção de uma pessoa jurídica que não foi indicada:
INSERT INTO PESSOAS (COD_PESSOA, CPF_CNPJ, NOME, NASCIMENTO, TIPO_PESSOA, NUMERO_FILHOS, IND_CLIENTE_BANCO, TIPO_LOGRADOURO, LOGRADOURO, BAIRRO, CIDADE, UF, PAIS, CEP, OBVERVACAO)
VALUES              ('002', '22222222222222', 'Empresa do William', '2005-01-01', 'J', DEFAULT, 'N', 'Casa', 'Rua 19, 8456 - CJ', 'Cidade Jardim', 'Rio Claro', 'SP', 'Brasil', '11111111', NULL)

--Inserção de uma pessoa física para ser alterada e removida:
INSERT INTO PESSOAS (COD_PESSOA, CPF_CNPJ, NOME, NASCIMENTO, TIPO_PESSOA, NUMERO_FILHOS, IND_CLIENTE_BANCO, TIPO_LOGRADOURO, LOGRADOURO, BAIRRO, CIDADE, UF, PAIS, CEP, OBVERVACAO)
VALUES              ('003', '33333333333', 'Bill Gates', '1966-01-01', 'F', DEFAULT, 'S', 'Casa', '9th Avenue, 692 - Manhattan', 'Manhattan', 'New York', 'NY', 'EUA', '22222222', NULL)

--Alteração da pessoa recém-criada:
UPDATE  PESSOAS 
SET     TIPO_PESSOA = 'J',
        LOGRADOURO  = 'Prédio',
        NASCIMENTO  = '1999-01-01'
WHERE   COD_PESSOA  = '003'

--Remoção da pessoa recém-criada:
DELETE 
FROM    PESSOAS 
WHERE   COD_PESSOA  = '003'

--Inserção de uma conta para a pessoa cuja chave primária é 001:
INSERT INTO CONTAS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, COD_PESSOA, IND_POUPANCA, USUARIO_INCLUSAO, DATA_INCLUSAO, USUARIO_ALTERACAO, DATA_ALTERACAO)
VALUES             ('001', '00001', '1111111', '001', 'N', 'Elon Musk', '2021-10-20', NULL, NULL)

--Inserção de uma conta para a pessoa cuja chave primária é 002:
INSERT INTO CONTAS (COD_COLIGADA, COD_AGENCIA, NRO_CONTA, COD_PESSOA, IND_POUPANCA, USUARIO_INCLUSAO, DATA_INCLUSAO, USUARIO_ALTERACAO, DATA_ALTERACAO)
VALUES             ('002', '00001', '2222222', '002', 'S', 'Tim Cook', '2021-10-25', NULL, NULL)

--Consulta que retorna a quantidade de pessoas cadastradas:
SELECT  COUNT(COD_PESSOA) AS QUANTIDADE
FROM    PESSOAS

--Consulta que retorna o nome e o tipo das pessoas cadastradas:
SELECT NOME, 
    CASE
        WHEN    TIPO_PESSOA = 'F' THEN 'Física'
        WHEN    TIPO_PESSOA = 'J' THEN 'Jurídica'
        ELSE    'Indefinido'
    END AS TIPO
FROM PESSOAS

--Consulta que retorna a pessoa que possui a menor data de nascimento:
SELECT  COD_PESSOA, NOME, NASCIMENTO
FROM    PESSOAS
WHERE   NASCIMENTO = (SELECT  MIN(NASCIMENTO)
                      FROM    PESSOAS);

--Consulta que retorna as pessoas ordenadas por UF e CIDADE, desde que a UF e a CIDADE sejam diferentes de nulo ou vazio:
SELECT      COD_PESSOA, NOME, UF, CIDADE
FROM        PESSOAS
WHERE       UF      IS NOT NULL AND
            CIDADE  IS NOT NULL AND
            UF      <> '' AND
            CIDADE  <> ''
ORDER BY    UF, CIDADE

--Consulta que retorna o nome e o número da conta das pessoas cadastradas:
SELECT      PESSOAS.NOME, CONTAS.NRO_CONTA
FROM        PESSOAS
INNER JOIN  CONTAS
ON          PESSOAS.COD_PESSOA = CONTAS.COD_PESSOA

--Consulta que retorna as contas das pessoas que tem mais de uma conta:
SELECT  COD_COLIGADA, COD_AGENCIA, NRO_CONTA
FROM    CONTAS
WHERE   COD_PESSOA IN (SELECT   COD_PESSOA
                       FROM     CONTAS
                       GROUP BY COD_PESSOA
                       HAVING   COUNT(NRO_CONTA) > 1)

--Consulta que retorna as contas das pessoas físicas:
SELECT      CONTAS.COD_COLIGADA, CONTAS.COD_AGENCIA, CONTAS.NRO_CONTA
FROM        PESSOAS
INNER JOIN  CONTAS
ON          PESSOAS.COD_PESSOA = CONTAS.COD_PESSOA
WHERE       PESSOAS.TIPO_PESSOA = 'F'