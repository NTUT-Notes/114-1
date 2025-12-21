def float_to_ieee754(num: float) -> str:
    # Handle sign
    if num < 0:
        sign = "1"
        num *= -1
    else:
        sign = "0"

    exponent = 0

    # Normalize to 1.XXX
    while int(num) > 1:
        num /= 2
        exponent += 1
    
    while int(num) < 1:
        num *= 2
        exponent -= 1

    # Convert exponent and fraction
    exponent_part = dec_bin_intager(exponent+127, 8)
    farction_part = dec_bin_float(num-1, 23)

    return f"{sign} {exponent_part} {farction_part}"


def dec_bin_float(num: float, length: int):
    if length <= 0:
        return ""
    
    num *= 2

    if num > 1:
        return "1" + dec_bin_float(num-1, length-1) 
    else:
        return "0" + dec_bin_float(num, length-1)

def dec_bin_intager(num: int, length: int):
    if length <= 0:
        return ""
    
    if num % 2 == 1:
        return dec_bin_intager(num // 2, length-1) + "1"
    else:
        return dec_bin_intager(num // 2, length-1) + "0"

def main():
    num = float(input())
    print( float_to_ieee754(num) )
    return

if __name__ == "__main__":
    main()
    