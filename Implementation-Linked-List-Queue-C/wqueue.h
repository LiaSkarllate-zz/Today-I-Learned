#pragma once

//Inclusions:
#include <stdio.h>
#include <stdlib.h>

//Data structs:
typedef struct WData {
    int key;
}WData;

typedef struct WNode {
    WData data;
    struct WNode* next;
}WNode;

typedef struct WQueue {
    struct WNode* front;
    struct WNode* rear;
}WQueue;

//Headers:
WNode* createWNode(WData data);
WQueue* createWQueue();
void printWQueue(WQueue* queue);
void addWNode(WQueue* queue, WData data);
WNode* removeWNode(WQueue* queue);