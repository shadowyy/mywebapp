package mywebapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.shadow.util.ReadFileUtil;

/**
 * Created by alice on 2016/12/10 16:36
 */
public class TestJsonSchema {

    private final static JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

    public static void main(String[] args) throws Exception {
        JsonNode mainNode = JsonLoader.fromString(ReadFileUtil.toString("/json/title/template.json"));
        JsonNode instanceNode = JsonLoader.fromString(ReadFileUtil.toString("/json/title/schema/schema.json"));
        JsonSchema schema = factory.getJsonSchema(mainNode);
        ProcessingReport processingReport = schema.validate(instanceNode);

        System.out.println(processingReport);

    }
}
