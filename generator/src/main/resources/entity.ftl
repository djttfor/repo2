package ${classPath};

public class ${className} implements Serializable {
    <#list  fields as field>
    <#if field.name == 'username' >@Resource</#if>
    private ${field.type}  ${field.name};
    </#list>

 <#list  fields as field>

    public void set${field.name?cap_first}(${field.type} ${field.name}){
         this.${field.name} = ${field.name};
    }

    public ${field.type} get${field.name?cap_first}(){
         return this.${field.name};
    }

 </#list>

}