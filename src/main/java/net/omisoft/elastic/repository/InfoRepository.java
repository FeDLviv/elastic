package net.omisoft.elastic.repository;

import net.omisoft.elastic.entity.Info;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends ElasticsearchRepository<Info, String> {

}
