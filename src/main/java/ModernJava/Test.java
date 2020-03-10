package ModernJava;

public class Test {

    public static void main(String[] args) {

        int a = ((Action)(i -> i*i))
                .chain((i) -> {
                    System.out.println("plus function");
                    return i+i;
                })
                .chain((i) -> {
                    System.out.println("plus 1");
                    return 1;
                }).apply(5);

        System.out.println(a);
    }


    @FunctionalInterface
    public interface Action {
        Integer apply(Integer value);
        default Action chain(Action anotherAction) {
            return (Integer t) -> apply(anotherAction.apply(t));
        }
    }


}
