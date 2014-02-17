

# 创建Model类执行，执行以下命令创建数据库DDL:

```
mvn test-compile hibernate3:hbm2ddl

```

# 创建DAO后以及测试类之后，执行下面的命令进行测试，其中的PersonDaoTest是测试类的名称:

```
 mvn test -Dtest=PersonDaoTest

```

如果以上命令报错，可以通过加入 -Dsurefire.useFile=false参数将错误显示在控制台上


```
 mvn test -Dtest=PersonDaoTest -Dsurefire.useFile=false

```

#创建Manager后以及对应测试类，执行下面的命令，其中PersonManagerImplTest时测试类的名称:

```
 mvn test -Dtest=PersonManagerImplTest
```