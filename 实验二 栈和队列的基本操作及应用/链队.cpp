#include <iostream>
#include <cstdlib>
using std::cout;
using std::endl;

struct LinkNode {
    int value;
    LinkNode *next = NULL;
};

class LinkedQueue {
    LinkNode *front, *rear;

  public:
    LinkedQueue() : front(NULL), rear(NULL) {}

    void Enque(int x) {
        if (rear == NULL) {
            LinkNode *n = new LinkNode;
            n->value = x;
            n->next = NULL;
            rear = n;
            front = n;
        } else {
            LinkNode *n = new LinkNode;
            n->value = x;
            n->next = NULL;
            rear->next = n;
            rear = n;
        }
    }

    void Deque() {
        if (rear == NULL && front == NULL) {
            cout << "\nUnderflow";
        } else {
            LinkNode *t = front;
            cout << "\n" << t->value << " deleted";
            front = front->next;
            delete t;
            if (front == NULL)
                rear = NULL;
        }
    }
};

int main() {
    LinkedQueue q;
    std::srand(233333);
    for (int i(0); i < 50; ++i) {
        q.Enque(std::rand());
        q.Enque(std::rand());
        q.Deque();
    }
    
    return 0; 
}
