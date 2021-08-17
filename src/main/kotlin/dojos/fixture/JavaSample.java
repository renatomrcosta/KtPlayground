package dojos.fixture;

import java.lang.reflect.InvocationTargetException;

class MyFunHttpClient {
    public String get(final String url) {
        return "Hey, I just did a get!";
    }

    <T> T getObject(final String url, final Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class SomeDTO {
    private final String id;

    SomeDTO(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

public class JavaSample {
    public static void main(String[] args) {
        final var myFunHttpClient = new MyFunHttpClient();
        final String someResult = myFunHttpClient.get("some url");
        System.out.println(someResult);

        final SomeDTO someDTOResult = myFunHttpClient.getObject("some url", SomeDTO.class);
        System.out.println(someDTOResult);
    }
}
