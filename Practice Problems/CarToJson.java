import com.fasterxml.jackson.databind.ObjectMapper;
public class CarToJson {
    public static void main(String[] args)
    {
        try
        {
            class Car {
                private String make;
                private String model;
                private int year;
                public Car(String make, String model, int year)
                {
                    this.make = make;
                    this.model = model;
                    this.year = year;
                }
                public String getMake()
                {
                    return make;
                }
                public void setMake(String make)
                {
                    this.make = make;
                }
                public String getModel()
                {
                    return model;
                }
                public void setModel(String model)
                {
                    this.model = model;
                }
                public int getYear()
                {
                    return year;
                }
                public void setYear(int year)
                {
                    this.year = year;
                }
            }
            Car car = new Car("Toyota", "Corolla", 2022);
            ObjectMapper objectMapper = new ObjectMapper();
            String carJson = objectMapper.writeValueAsString(car);
            System.out.println("Car object in JSON format: ");
            System.out.println(carJson);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
