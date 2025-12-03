fun main() {
    for (i in 0..71) {
        var row = i / 9 + 1;
        var col = i % 9 + 1;
        
       	lateinit var exp: String;
        
        if (col == 1) {
            exp = "%dx%d=%d ";
        } else {
            exp = "%dx%d=%2d ";
        }
        
        print( String.format(exp, row, col, row*col) );
        
        if (col == 9) {
            println();
        }
    }
}