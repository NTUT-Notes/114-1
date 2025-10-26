public class Part_1 {
    public static void main(String[] args) {
        String data = """
成婉財 27 91 21 33 13
翁雅婷 96 90 40 55 69
袁維茹 38 85 72 13 34
黃士哲 81 40 24 93 79
郭珮珊 72 33 32 83 73
陳儀琬 78 55 22 41 62
李碧彥 30 48 13 93 70
梁健玉 23 89 10 44 24
許雅淑 90 11 33 27 67
蕭宛新 29 64 64 90 43
        """;
        

        for (String line : data.split("\n")) {
            String[] res = line.split(" ");

            System.out.printf("%s\n%s, %s, %s, %s, %s\n", res[0], res[1], res[2], res[3], res[4], res[5]);

        }
    }
}
