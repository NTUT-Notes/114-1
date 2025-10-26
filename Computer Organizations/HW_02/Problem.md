# 一、BMI Calculator (35%)
```c
#include <stdlib.h>
#include <stdio.h>

int calculateBMI(int height, int weight) {
  int bmi = (weight * 10000) / (height * height);
  return bmi;
}

void printResult (int bmi) {
  if (bmi <= 17)
    printf("%s", "underweight\n");
  else if (bmi >= 25)
    printf("%s", "overweight\n");
  else
    printf("%d\n", bmi);
}

int main() {
  int height, weight, bmi;
  while (1) {
    scanf("%d", &height);
    if (height == -1)
      break;
    scanf("%d", &weight);
    bmi = calculateBMI(height, weight);
    printResult(bmi);
  }
  return 0;
}
```

## Case 1
```
170
53
180
45
130
70
-1
```

```
18
underweight
overweight
```

## Case 2
```
165
66
173
44
168
60
-1
```
```
24
underweight
21
```

## Case 3
```
152
69
199
98
175
40
-1
```
```
overweight
24
underweight
```

# 二、Recursion (35%)
```c
#include <stdio.h>
void print(int size, int *x) {
  for (int i=0; i<size; i++) {
    printf("%d,",x[i]);
  }
}

int fact (int n, int *x) {
  int t=0;
  
  if (n < 2) {
    x[n] = t = 1;
    return t;
  } 
  else {
    x[n] = t = fact(n - 1, x) + fact(n - 2, x);
    return t;
  }
}

int main() {
  int x[100];
  int n;
  x[0]=1;
  for (int i=1; i<100; i++)
    x[i]=0;
  scanf("%d", &n);
  fact(n, x);
  print(n, x);
  return 0;
}
```

## Case 1
```
3
```
```
1,1,2,
```

## Case 2
```
5
```
```
1,1,2,3,5,
```

## Case 3
```
8
```
```
1,1,2,3,5,8,13,21,
```

# 三、Selection sort (30%)
```c
#include <stdlib.h>
#include <stdio.h>

void selectionSort(int array[], int n) {
  for (int i=0; i<n-1; i++) {
    int min_idx = i;
    for (int j=i+1; j<n; j++) {
      if (array[j] < array[min_idx]) {
        min_idx = j;
      }
    }
    int temp = array[min_idx];
    array[min_idx] = array[i];
    array[i] = temp;
  }
}

int main() {
  int array[5];
  for (int i = 0; i < 5; i++) {
    scanf("%d", &array[i]);
  }
  selectionSort(array, 5);
  for (int i = 0; i < 5; i++) {
    printf("%d\n", array[i]);
  }
  return 0;
}
```

## Case 1
```
6
10
8
2
4
```
```
2
4
6
8
10
```

## Case 2
```
55
44
33
22
11
```
```
11
22
33
44
55
```

## Case 3
```
2
3
4
1
5
```
```
1
2
3
4
5
```

# 四、Transpose of a Matrix (35%)
```c
#include <stdlib.h>
#include <stdio.h>
void inputMatrix(int A[3][3]) {
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      scanf("%d", &A[i][j]);
    }
  }
}

void transposeMatrixA1(int A[3][3], int T[3][3], int size) {
  for (int i = 0; i < size; i++) {
    for (int j = 0; j < size; j++) {
      T[j][i] = A[i][j];
    }
  }
}

void transposeMatrixA2(int *B, int *T, int size) {
  int *ptrB, *ptrT, i;
  for (ptrB=B, ptrT=T, i = 1; ptrB<(B + (size*size)); ptrB++) {
    *ptrT = *ptrB;
    if (i < size) {
      ptrT += size;
      i++;
    }
    else {
      ptrT -= (size * (size - 1) - 1);
      i = 1;
    }
  }
}

void outputMatrix(int A[3][3]) {
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      printf("%d ", A[i][j]);
    }
    printf("\n");
  }
}

int main() {
  int A[3][3];
  int transposeOfA1[3][3];
  int transposeOfA2[3][3];
  int *ptrA = &A[0][0];
  int *ptrTA2 = &transposeOfA2[0][0];
  inputMatrix(A);
  transposeMatrixA1(A, transposeOfA1, 3);
  transposeMatrixA2(ptrA, ptrTA2, 3);
  outputMatrix(transposeOfA1);
  outputMatrix(transposeOfA2);
  return 0;
}
```

## Case 1

```
1
2
3
4
5
6
7
8
9
```
```
1 4 7
2 5 8
3 6 9
1 4 7
2 5 8
3 6 9
```

## Case 2
```
11
44
77
22
55
88
33
66
99
```

```
11 22 33
44 55 66
77 88 99
11 22 33
44 55 66
77 88 99
```

## Case 3
```
63
72
58
43
88
56
79
51
12
```
```
63 43 79
72 88 51
58 56 12
63 43 79
72 88 51
58 56 12
```