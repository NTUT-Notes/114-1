# Function to print all tupless present in a list with a given sum
def all_tuples(A: list, target: int):
    result = []
    
    length = len(A)

    for a in range(length):
        for b in range(a+1, length):
            for c in range(b+1, length):
                for d in range(c+1, length):
                    if A[a] + A[b] + A[c] + A[d] == target:
                        result.append( tuple(sorted([A[a], A[b], A[c], A[d]])) )
    
    result.sort()

    return result
    
# Driver script
if __name__ == '__main__':
    
    A = [12, 21, 8, 7, 2, 5, 16, 19, 25, 14, 10]
    S = 34
    tuples=all_tuples(A, S)
    print(f"The input list is {A} and tuples summing to {S} are: {tuples}")

    S = 80
    tuples=all_tuples(A, S)
    print(f"The input list is {A} and tuples summing to {S} are: {tuples}")
    
    A = [2, 7, 4, 0, 9, 5, 1, 3]
    S = 20
    tuples=all_tuples(A, S)
    print(f"The input list is {A} and tuples summing to {S} are: {tuples}")