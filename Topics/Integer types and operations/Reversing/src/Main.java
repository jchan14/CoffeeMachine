import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int h = a / 100;
        int t = ( a % 100) / 10;
        int d = a % 10;
        
        int output = d * 100 + t * 10 + h;
        System.out.print(output);
        
    }
}
