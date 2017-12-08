package day_05;

import java.util.List;
/*Итерация один сериализация по умолчанию
* Итерация два дописать в конец файла сумму всех зарплат
* Итерация три пользовательская сериализация
* Итерация четыре saveOrUpdate(name)*/
/*Итерация пять change all work using BufferingStreams
*
* решение должно быть грамотно разбито на классы, структурировано, работа с ошибками
* подумать над тем что возможно стоит держать стрим открытым*/
public class Employe {
    private String name;
    private String age;
    private String salary;
    private String job;
}

class EmployeManager {
    public void save(Employe emp) {}
    public void delete (Employe emp){}
    public void getByName (String name){}
    public List<Employe> getByJob(String job){
        return null;
    }
}
