package ${basePkg}.service;


import ${basePkg}.domain.${className};
import ${basePkg}.query.PageResult;
import ${basePkg}.query.QueryObject;

import java.util.List;

import com._520it.ssh.query.PageQuery;

public interface I${className}Service {

    void save(${className} ${objectName});

    void update(${className} ${objectName});

    void delete(Long id);

    ${className} get(Long id);

    List<${className}> listAll();

    PageQuery query(QueryObject qo);
}
