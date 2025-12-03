fun main() {
    var rawData = 
"""邱冠勛 98 64 52 73 81
成婉財 27 91 21 33 13
翁雅婷 96 90 40 55 69
袁維茹 38 85 72 13 34
黃士哲 81 40 24 93 79
郭珮珊 72 33 32 83 73
陳儀琬 78 55 22 41 62
李碧彥 30 48 13 93 70
梁健玉 23 89 10 44 24
許雅淑 90 11 33 27 67
蕭宛新 29 64 64 90 43""";
    
    var output = rawData.split("\n");
	var header =
"""<table border="1" cellpadding="3">
  <caption> %s </caption>
""";
    
    var footer = 
"""</table>
""";

    var exp = 
"""    <tr>
      <th> %s </th> <th> %s </th> <th> %s </th> <th> %s </th> <th> %s </th> <th> %s </th>
    </tr>""";
    
    
    println(String.format(header, "邱冠勛"));
    for (context in output) {
        var cells = context.split(" ");
        println(String.format(exp, cells[0], cells[1], cells[2], cells[3], cells[4], cells[5]));
    }
    println(footer);
}