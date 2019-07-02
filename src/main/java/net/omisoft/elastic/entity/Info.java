package net.omisoft.elastic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.UUID;

@Document(indexName = "metrics", type = "info")
@Data
@Builder(builderClassName = "InfoBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    @Id
    private String id;

    @Field(type = FieldType.Date)
    private Date date;

    private String name;

    //TODO add property
    private String node;

    private Long duration;

    public static class InfoBuilder {

        private String id = UUID.randomUUID().toString();

        private Date date = new Date();
    }

}
