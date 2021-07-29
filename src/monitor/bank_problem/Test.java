package monitor.bank_problem;

class BankAccount
{
  int balance;
  boolean p_turn = true;
  
  synchronized void deposit(int n)
  {
    int temp = balance + n; //의도적인 시간지연
    System.out.print("+"); //의도적인 시간지연
    balance = temp;
    
    p_turn = false;
  }

  synchronized void withdraw(int n)
  { 
	int temp = balance - n; //의도적인 시간지연
	System.out.print("-"); //의도적인 시간지연
	balance = temp;
	
	p_turn = true;
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