//Includes:
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

//Constants:
#define LENGTH  10 
#define SEED    0
#define BEST    0
#define WORST   1

//Auxiliary sorting functions:
void swap(long int array[], long int indexA, long int indexB) {
    long int temp = array[indexA];
    array[indexA] = array[indexB];
    array[indexB] = temp;
}

long int indexOfMinimum(long int array[], long int startIndex) {
    //Variables:
    long int minValue = array[startIndex];
    long int minIndex = startIndex;
    long int i;

    //Logic:
    for(i = minIndex + 1; i < LENGTH; i++) {
        if(array[i] < minValue) {
            minIndex = i;
            minValue = array[i];
        }
    } 
  
    return minIndex;
}

void insertInSubarray(long int array[], long int rightIndex, long int value) {
    //Variables:
    long int i;
    
    //Logic:
    for(i = rightIndex; (i >=0) && (array[i] > value); i--) {
        array[i + 1] = array[i];
    }   
    
    array[i + 1] = value;
};

void merge(long int array[], long int p, long int q, long int r) {
	//Variables:
    long int lengthLowHalf = q - p + 1;
    long int lengthHighHalf = r - q;
  
  	long int i;
  	long int j;
    long int k;
  
  	long int* lowHalf = (long int*) malloc(lengthLowHalf * sizeof(long int));
    long int* highHalf = (long int*) malloc(lengthHighHalf * sizeof(long int));
  
    //Logic:
        //Cópia da primeira metade do 'array' para 'lowHalf':
    for(i = 0; i < lengthLowHalf; i++) {
        lowHalf[i] = array[p + i];
    }
        
    //Cópia da segunda metade do 'array' para 'highHalf' de maneira intertida:
    for(j = 0; j < lengthHighHalf; j++) {
      	highHalf[j] = array[q + 1 + j];
    }
  
    i = 0;
    j = 0;
    k = p;
  
  	while(i < lengthLowHalf && j < lengthHighHalf) {
  	  	if (lowHalf[i] <= highHalf[j]) {
  	  	  	array[k] = lowHalf[i];
  	  	  	i++;
  	  	}else{
            array[k] = highHalf[j];
            j++;
    	  }
        k++;
    }
  
    while(i < lengthLowHalf) {
        array[k] = lowHalf[i];
        i++;
        k++;
    }
  
    while(j < lengthHighHalf) {
        array[k] = highHalf[j];
        j++;
        k++;
    }
}

long int split(long int array[], long int p, long int r) {
    //Variables:
   	long int pivot = array[r];
   	long int t, j = p;
  	long int k;
  
    //Logic:
    for(k = p; k < r; ++k) {
        if(array[k] <= pivot) {
            t = array[j];
            array[j] = array[k];
            array[k] = t;
                
            ++j; 
        } 
    }
  
    t = array[j];
    array[j] = array[r];
    array[r] = t;
  
    return j; 
}

void heapify(long int array[], long int n, long int i) {
  	//Variables:
    long int max = i;
    long int leftChild = 2 * i + 1;
    long int rightChild = 2 * i + 2;
  
  	//Logic:
  	if(leftChild < n && array[leftChild] > array[max]) {
        max = leftChild;
  	}
  
    if(rightChild < n && array[rightChild] > array[max]) {
        max = rightChild;
    }
  
  	if(max != i) {
        swap(array, i, max);
        heapify(array, n, max);
  	}
}

//Sorting functions:
void selectionSort(long int array[]) {
    //Variables:
    long int minIndex;
    long int i;

    //Logic:
    for(i = 0; i < LENGTH; i++) {
        minIndex = indexOfMinimum(array, i);
        swap(array, i, minIndex);
    }
}

void insertionSort(long int array[]) {
    //Variables:
    long int i;

    //Logic:
    for(i = 1; i < LENGTH; i++) {
        insertInSubarray(array, i - 1, array[i]);
    }
}

void mergeSort(long int array[], long int p, long int r) {
    if(p < r) {
        long int q = floor((p + r)/2);
        mergeSort(array, p, q);
        mergeSort(array, q + 1, r);
        merge(array, p, q, r);
    }
}

void heapSort(long int array[]) {
    for(int i = LENGTH / 2 - 1; i >= 0; i--) {
        heapify(array, LENGTH, i); 
    }
 
    for(int i = LENGTH - 1; i >= 0; i--) {
        swap(array, 0, i); 
        heapify(array, i, 0); 
    }
}

void quickSort(long int array[], long int p, long int r) {
    if (p < r) {                
        long int j = split(array, p, r);
        quickSort(array, p, j - 1);     
        quickSort(array, j + 1, r);
     }
}

//Auxiliary functions:
void printArray(long int array[]) {
    //Variables:
    long int i;

    for(i = 0; i < LENGTH; i++) {
        printf("%ld, ", array[i]);
    }

    printf("\n\n");
}

void invertArray(long int array[]) {
    //Variables:
    long int i;
    long int arrayAux[LENGTH];

    //Logic:
    for (i = 0; i < LENGTH; i++) {
        arrayAux[LENGTH - 1 - i] = array[i];
    }

    for (i = 0; i < LENGTH; i++) {
        array[i] = arrayAux[i];
    }
}

//Main function:
int main() {
    //Variables:
    long int i;
    long int generated;

    double timeSelectionSort;
    double timeInsertionSort;
    double timeMergeSort;
    double timeHeapSort;
    double timeQuickSort;

    long int* array0 = (long int*) malloc(LENGTH * sizeof(long int));
    long int* array1 = (long int*) malloc(LENGTH * sizeof(long int));
    long int* array2 = (long int*) malloc(LENGTH * sizeof(long int));
    long int* array3 = (long int*) malloc(LENGTH * sizeof(long int));
    long int* array4 = (long int*) malloc(LENGTH * sizeof(long int));

    printf("LENGTH = %ld elements \n", LENGTH);
    printf("BEST = %ld \n", BEST);
    printf("WORST = %ld \n", WORST);
    printf("SEED = %ld \n\n", SEED);

    //Logic:
        //Array filling:
    if(BEST || WORST) {
        for(i = 0; i < LENGTH; i++) {
            generated = rand() % LENGTH;
            array0[i] = generated;
        }

        quickSort(array0, 0, LENGTH - 1);

        if(WORST) {
            invertArray(array0);
        }

        for(i = 0; i < LENGTH; i++) {
            array1[i] = array0[i];
            array2[i] = array0[i];
            array3[i] = array0[i];
            array4[i] = array0[i];
        }
    } else {
        srand(SEED);

        for(i = 0; i < LENGTH; i++) {
            generated = rand() % LENGTH; 

            array0[i] = generated;
            array1[i] = generated;
            array2[i] = generated;
            array3[i] = generated;
            array4[i] = generated;
	    }
    }

    //Array printing (before):
    printArray(array0);
    printArray(array1);
    printArray(array2);
    printArray(array3);
    printArray(array4);

    //Array sorting:
    clock_t startSelectionSort = clock();
        selectionSort(array0);
    clock_t endSelectionSort = clock();

    timeSelectionSort = (double) (endSelectionSort - startSelectionSort) / CLOCKS_PER_SEC;
    //printf("timeSelectionSort = %f seconds\n", timeSelectionSort);

    clock_t startInsertionSort = clock();
        insertionSort(array1);
    clock_t endInsertionSort = clock();

    timeInsertionSort = (double) (endInsertionSort - startInsertionSort) / CLOCKS_PER_SEC;
    //printf("timeInsertionSort = %f seconds\n", timeInsertionSort);

    clock_t startMergeSort = clock();
        mergeSort(array2, 0, LENGTH - 1);
    clock_t endMergeSort = clock();

    timeMergeSort = (double) (endMergeSort - startMergeSort) / CLOCKS_PER_SEC;
    //printf("timeMergeSort = %f seconds\n", timeMergeSort);

    clock_t startHeapSort = clock();
        heapSort(array3);
    clock_t endHeapSort = clock();

    timeHeapSort = (double) (endHeapSort - startHeapSort) / CLOCKS_PER_SEC;
    //printf("timeHeapSort = %f seconds\n", timeHeapSort);

    clock_t startQuickSort = clock();
        quickSort(array4, 0, LENGTH - 1);
    clock_t endQuickSort = clock();

    timeQuickSort = (double) (endQuickSort - startQuickSort) / CLOCKS_PER_SEC;
    //printf("timeQuickSort = %f seconds\n", timeQuickSort);

    //Array printing (after):
    printArray(array0);
    printArray(array1);
    printArray(array2);
    printArray(array3);
    printArray(array4);

    return 0;
}
