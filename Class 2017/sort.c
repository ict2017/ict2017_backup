#include <stdio.h>

int main(){
    int n;
    int i = 0;
    int x = 0;
    int t = 0;

	printf("Input number of values to be sorted (0 < N < 100): ");
	scanf("%d",&n);
	int array[n];
	while(i<n){
		printf("Input value #%d ", i+1);
		scanf("%d",&array[i]);
		i++;
	}
	
	i=1;
	while(i<n){
		x=i;
		while (x > 0 && array[x] < array[x - 1]) {
			t = array[x];
			array[x] = array[x-1];
			array[x-1] = t;
			x--;
	}
	i++;
	}
	i=0;
	
    while(i<n){
        printf("%d ", array[i]);
		i++;
	}
    return 0;
}
