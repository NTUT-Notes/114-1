
fun main() {
  print("Please enter height (cm): ");
  var height: Double = readln().toDouble() / 100;
        
  print("Please enter weight (kg): ");
  var weight: Double = readln().toDouble();
        
  println(String.format("Your BMI is %.2f", weight / (height * height)))
}