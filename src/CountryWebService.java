import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Path("/country")
public class CountryWebservice {
    //определение размера контейнера
    private static final int CONTAINER_SIZE = 10;
    //генератор случайных чисел
    private static final Random RANDOM = new Random();

    //создание пустой внутренней с  труктуры данных
    private static Map<Integer, lab2.country.Country> container = new HashMap<>();

    //инициализация структуры данных заполненными объектами
    static {
        for (int i = 0; i < CONTAINER_SIZE; i++) {
            int randomId = generateRandomNumber();
            float area = generateRandomFloat();
            container.put(randomId, new lab2.country.Country("Ukraine", 38000000, randomId, area));
        }
    }

    // метод для генерации случайных числе по указанному диапазону
    private static int generateRandomNumber() {
        return RANDOM.nextInt(CONTAINER_SIZE * CONTAINER_SIZE - CONTAINER_SIZE + 1) + CONTAINER_SIZE;
    }

    private static float generateRandomFloat() {
        return RANDOM.nextFloat();
    }

    // возвращает объект по его идентификатору
    @GET


    @Path("/{getById}")
    public Response getPersonById(@PathParam("id") long id) {
        return Response
                .status(Response.Status.OK) //установка статуса ответа
                .entity(container.get(id)) //добавление объекта в тело REST -ответа
                .build(); //завершение конструирования объекта
    }

    //возвращает список всех объектов
    @GET
    @Path("/{getAll}")
    public Response getAllCountries() {
        return Response
                .status(Response.Status.OK) //установка статуса ответа
//добавление объекта в тело REST-ответа
                .entity(container.values().toArray(new
                        Country[container.values().size()]))
                .build(); //завершение конструирования объекта
    }

    @DELETE
    @Path("/{delete}")
    public Response removeCountryById(long id) {
        boolean countryExists = container.containsKey(id);
        container.remove(id);
        return Response.status((countryExists ||
                container.containsKey(id)) ? //проверка условия был ли объект удален
                Response.Status.OK : //удален - статус ОК
                Response.Status.NOT_ACCEPTABLE) //не удален - статус ошибки
                .entity(null) //пустое тело REST-ответа

                .build(); // завершение конструирования объекта
    }

    @PUT
    @Path("/{update}")
    public Response updatePerson(Country Country) {
        Country personToUpdate = container.get(Country.getId());
        boolean isUpdated = false;
        if (personToUpdate == null) {
            isUpdated = false;
        } else {
            personToUpdate.setName(Country.getName());
            personToUpdate.setAge(Country.getAge());
            isUpdated = true;
        }
        return Response.status(isUpdated ? //проверка условия успешности обновления объекта
                Response.Status.OK : //ок - объект обновлен
                Response.Status.NOT_ACCEPTABLE) //объект обновить не удалось
                .entity(null) //пустое тело REST-ответа
                .build(); //завершение конструирования объекта
    }
}