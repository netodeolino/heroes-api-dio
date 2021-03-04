package com.br.heroesapidio.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.Arrays;

import static com.br.heroesapidio.constants.HeroesConstant.*;

public class HeroesTable {

    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        try {
//            Table table = dynamoDB.getTable(HEROES_TABLE_TABLE);
//            table.delete();
//            table.waitForDelete();

            System.out.println("Criando tabela, aguarde...");

            Table table = dynamoDB.createTable(HEROES_TABLE_TABLE,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5L));

            table.waitForActive();
            System.out.println("Sucesso " + table.getDescription().getTableStatus());
        } catch (Exception e) {
            System.err.println("Não foi possível criar a tabela");
            System.err.println(e.getMessage());
        }
    }

}
