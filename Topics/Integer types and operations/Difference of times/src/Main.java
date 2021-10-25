import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();
        int e = scanner.nextInt();
        int f = scanner.nextInt();
        int start = a*60*60+b*60+c;
        int end = d*60*60+e*60+f;
        System.out.print(end-start);
    }
}
