public class Part_4 {
    public static void main(String[] args) {
        Account account1 = new Account("邱大牛", 1000);

        account1.show();
        System.out.println();

        account1.deposit(100);
        System.out.println();
        
        account1.withdraw(200);
        System.out.println();

        account1.withdraw(9999);
        System.out.println();
    }
}

class Account {
    private String name;
    private int assets;
    private String uuid;

    public static int instanceCount = 0;

    Account(String name, int money) {
        this.uuid = "Bank" + instanceCount;
        this.name = name;
        this.assets = money;

        instanceCount ++;
    }

    public void deposit(int money) {
        System.out.printf("您存入 %d 元！\n", money);
        this.assets += money;

        checkBalance();
    }

    public void withdraw(int money) {
        if (money > this.assets) {
            System.out.printf("想再提領 1000 元。\n", money);
            System.out.println("存款餘額不足！");
            checkBalance();
            return;
        }

        this.assets -= money;

        System.out.printf("您提領 %d 元！\n", money);
        checkBalance();
    }

    public void checkBalance() {
        System.out.printf("帳號: %s\n", this.uuid);
        System.out.printf("姓名: %s\n", this.name);
        System.out.printf("餘額: %d\n", this.assets);
    }

    public void show() {
        System.out.println("您的帳戶訊息:");
        checkBalance();
    }


}