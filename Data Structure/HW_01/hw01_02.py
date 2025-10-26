# Python’s random module includes a function choice that returns a random element from a non-empty sequence. 
# Use random.choice() to call it

import random
import pandas as pd

#Return a random element from a non-empty sequence using only randrange function.
def choice(data: list):
    return data[ random.randrange(0, len(data)) ]

def runChoice(data: list, count: int, func: 'function'):
    result = [0 for _ in data]
    for _ in range(count):
        num = func(data)
        result[ data.index(num) ] += 1

    return result

def showReport(data: list, result: list, count: int):
    random.choice
    
    df = pd.DataFrame({
        "item": data,
        "frequence": result,
        "percentage": [ "{:3.2f}%".format(i/count*100) for i in result]
    })
    print(df)

#
# Driver script
# One need to have the code for the output below
#
if __name__ == '__main__':
    # Example usage
    seq = [13, 23, 33, 43, 53]

    for cnt in [10, 100, 1000]:
        print(f"==Case of {cnt} items==")
        print("       My own choice")
        showReport(seq, runChoice(seq, cnt, choice), cnt)

        print("\n       The random choice")
        showReport(seq, runChoice(seq, cnt, random.choice), cnt)

        print()
