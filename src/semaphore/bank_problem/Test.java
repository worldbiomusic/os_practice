package semaphore.bank_problem;

import java.util.concurrent.Semaphore;

class BankAccount
{
    Semaphore sem;
    BankAccount()
    {
        sem = new Semaphore(1);
    }
  int balance;
  void deposit(int n)
  {
      try{
          sem.acquire();
      }catch(Exception e) {}
      
      ///////////////// critical section 임계구역
    int temp = balance + n; //의도적인 시간지연
    System.out.print("+"); //의도적인 시간지연
    balance = temp;
      ///////////////// critical section
      sem.release();
  }

  void withdraw(int n)
  {
      try{
          sem.acquire();
      }catch(Exception e) {}
      ///////////////// critical section
    int temp = balance - n; //의도적인 시간지연
    System.out.print("-"); //의도적인 시간지연
    balance = temp;
      ///////////////// critical section
      sem.release();
  }

  int getBalance()
  {
    return balance;
      
  }
}


class Parent extends Thread
{
  BankAccount b;
  Parent(BankAccount b)
  {
    this.b = b;
  }

  public void run()
  {
    for(int i = 0; i < 1000; i++){
      b.deposit(1000);
    }
  }
}


class Child extends Thread
{
  BankAccount b;
  Child(BankAccount b)
  {
    this.b = b;
  }

  public void run()
  {
    for(int i = 0; i < 1000; i++)
      b.withdraw(1000);
  }
}


class Test
{
  public static void main(String[] args) throws InterruptedException
  {
    BankAccount b = new BankAccount();
    Parent p = new Parent(b);
    Child c = new Child(b);
    p.start();
    c.start();
    
    p.join();
    c.join();
    System.out.print("\nBalance: " + b.getBalance());
  }
}
