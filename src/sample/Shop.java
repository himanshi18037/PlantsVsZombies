package sample;

public class Shop{

    private PlantTags[] currentlyAvailablePlants;

    public Shop(int level){

        if (level == 1){
            currentlyAvailablePlants = new PlantTags[1];
            currentlyAvailablePlants[0] = new PlantTags("PeaShooter");
        }
    }

    public PlantTags getPlant(int index){
        return currentlyAvailablePlants[index];
    }

    public class PlantTags{
        String name;
        boolean available = true;

        PlantTags(String name){
            this.name = name;
        }

        public boolean getAvailabilityStatus(){
            return available;
        }

        public void changeAvailabilityStatus(){
            available = !available;
        }
    }

}
