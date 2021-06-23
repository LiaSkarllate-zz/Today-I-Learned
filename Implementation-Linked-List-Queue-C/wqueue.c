//Header inclusions:
#include <wqueue.h>

//Utility functions:
    //Function to create a new linked list node:
WNode* createWNode(WData data){
    WNode* newNode = (WNode*) malloc(sizeof(WNode));

    newNode->data = data;
    newNode->next = NULL;

    return newNode;
}

//Function to create an empty queue:
WQueue* createWQueue(){
    WQueue* newQueue = (WQueue*) malloc(sizeof(WQueue));

    newQueue->front = newQueue->rear = NULL;
    
    return newQueue;
}

//Function to print a queue:
void printWQueue(WQueue* queue){
    //If queue is empty, then new node is front and rear both:
    if(queue->rear == NULL) {
        return;
    }

    WNode* next_node = queue->front;
  
    printf("Printing...\n");

    while(next_node != NULL){
        printf("%d\n", next_node->data.key);
        next_node = next_node->next;
    }

    printf("\n");
}

//WQueue functions:
    //Function to add a node to given queue:
void addWNode(WQueue* queue, WData data){
    WNode* newNode = createWNode(data);
  
    //If queue is empty, then new node is front and rear both:
    if(queue->rear == NULL) {
        queue->front = queue->rear = newNode;
        return;
    }
  
    //It adds the new node at the end of queue and change rear:
    queue->rear->next = newNode;
    queue->rear = newNode;
}

//Function to remove a node from given queue:
WNode* removeWNode(WQueue* queue){
    //If queue is empty, return NULL:
    if (queue->front == NULL){
        return NULL;
    }
  
    //It stores the previous front and move front one node ahead:
    WNode* removeWNode = queue->front;
    queue->front = queue->front->next;
  
    //If front becomes NULL, then change rear also as NULL:
    if (queue->front == NULL){
        queue->rear = NULL;
    }

    return removeWNode;
}