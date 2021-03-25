package lab2.country;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Random;

@XmlRootElement //аннотация для JAXB, чтобы он начал преобразовывать объект в XML и обратно
public class Country {
    private float area;
    private String name;
    private int population;
    private int id;

    //конструктор по-умолчанию, инициализирует переменные значениями по-умолчанию
    public Country() {
        this.name = "Test";
        this.area = 0;
        this.id = new Random().nextInt(); //генерация id при каждом создании объекта
        this.population = 0;
    }

    //параметризованный конструктор, позволяет задавать значения атрибутов при создании объекта
    public Country(String name, int population, int id, float area) {
        this.area = area;
        this.name = name;
        this.population = population;
        this.id = id;
    }

    /*набор getter/setter-ов для доступа к переменным объекта,
    также они в большинстве случаев нужны для корректной работы JAXB и прочих фреймворков*/
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPopulation() {
        return this.population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public float getArea() {
        return this.area;
    }
    public void setArea(float area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
