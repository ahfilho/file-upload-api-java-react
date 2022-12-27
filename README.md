A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária.
SELECT * FROM conta INNER JOIN transferencia WHERE ID=1

Caso não seja informado nenhum filtro, retornar todos os dados de transferência.
select * from transferencia
