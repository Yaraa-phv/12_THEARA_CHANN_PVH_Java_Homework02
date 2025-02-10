interface Flyable {
    void fly();
}
interface Swimable {
    void swim();
}
class Duck implements Flyable, Swimable {
    @Override
    public void fly() {
        System.out.println("Duck is flying.");}
    @Override
    public void swim() {
        System.out.println("Duck is swimming.");}
}

