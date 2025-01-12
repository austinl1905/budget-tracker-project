import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Account
{   private ArrayList<Transaction> transactions;
    private static int count = 0;
    private int accountNumber;
    private Date creationDate;
    private Scanner scan;

    public Account(ArrayList<Transaction> transactions)
    {   this.transactions = transactions;
        count++;
        this.accountNumber = count;
        this.creationDate = new Date();
        this.scan = new Scanner(System.in);
    }

    public Account()
    {   this(new ArrayList<Transaction>());   }

    public void addTransaction(Transaction transaction)
    {   this.transactions.add(transaction);
        System.out.println("Transaction successfully added");
    }

    public static void sortTransactionsByAmount(ArrayList<Transaction> transactions)
    {   if (transactions.size() <= 1) // To prevent sorting arrays further than necessary
        {   return;   } // Returns to parent call, not the first call.

        int midpoint = transactions.size() / 2;
        // subList() returns List, so I need to wrap it
        ArrayList<Transaction> leftArray = new ArrayList<>(transactions.subList(0, midpoint));
        ArrayList<Transaction> rightArray = new ArrayList<>(transactions.subList(midpoint, transactions.size()));

        // Recursively divide subarrays
        sortTransactionsByAmount(leftArray);
        sortTransactionsByAmount(rightArray);

        // Pass subarrays "up" the call stack to be merged
        ArrayList<Transaction> sortedTransactions = mergeTransactionsByAmount(leftArray, rightArray);
        transactions.clear();
        transactions.addAll(sortedTransactions); // Since merge sort doesn't work in place, the sorted array has to be copied to the original.
    }

    public static void sortTransactionsByDate(ArrayList<Transaction> transactions)
    {   if (transactions.size() <= 1) // To prevent sorting arrays further than necessary
        {   return;   } // Returns to parent call, not the first call.

        int midpoint = transactions.size() / 2;
        // subList() returns List, so I need to wrap it
        ArrayList<Transaction> leftArray = new ArrayList<>(transactions.subList(0, midpoint));
        ArrayList<Transaction> rightArray = new ArrayList<>(transactions.subList(midpoint, transactions.size()));

        // Recursively divide subarrays
        sortTransactionsByDate(leftArray);
        sortTransactionsByDate(rightArray);

        ArrayList<Transaction> sortedTransactions = mergeTransactionsByDate(leftArray, rightArray);
        transactions.clear();
        transactions.addAll(sortedTransactions);
    }

    private static ArrayList<Transaction> mergeTransactionsByAmount(ArrayList<Transaction> leftArray, ArrayList<Transaction> rightArray)
    {   ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftArray.size() && rightIndex < rightArray.size())
        {   if(leftArray.get(leftIndex).getAmount() < rightArray.get(rightIndex).getAmount())
            {   transactions.add(leftArray.get(leftIndex));
                leftIndex++;
            } else 
            {   transactions.add(rightArray.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < leftArray.size())
        {   transactions.add(leftArray.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < rightArray.size())
        {   transactions.add(rightArray.get(rightIndex));
            rightIndex++;
        }

        return transactions;
    }

    private static ArrayList<Transaction> mergeTransactionsByDate(ArrayList<Transaction> leftArray, ArrayList<Transaction> rightArray)
    {   ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftArray.size() && rightIndex < rightArray.size())
        {   if(leftArray.get(leftIndex).getDate().compareTo(rightArray.get(rightIndex).getDate()) < 0)
            {   transactions.add(leftArray.get(leftIndex));
                leftIndex++;
            } else 
            {   transactions.add(rightArray.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < leftArray.size())
        {   transactions.add(leftArray.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < rightArray.size())
        {   transactions.add(rightArray.get(rightIndex));
            rightIndex++;
        }

        return transactions;
    }

    public static void reverseTransactionList(ArrayList<Transaction> transactions)
    {   for (int i = 0; i < transactions.size() / 2; i++)
        {   Transaction temp = transactions.get(i);
            transactions.set(i, transactions.get(transactions.size() - 1 - i));
            transactions.set(transactions.size() - 1 - i, temp);
        }

    }

    public void open()
    {   boolean flag = true;
        while(flag)
        {   System.out.println("Enter number for the following actions:\n\t1: Add transaction\n\t2: Remove transaction\n\t3: View transaction log\n\t4: View statistics\n\t5: Sort transactions (Ascending)\n\t6: Sort transactions (Descending)\n\t7: Stop/Save transactions");
            int input = this.scan.nextInt();
            if (input == 1) 
            {   System.out.println("Enter transaction:");
                double transactionAmount = this.scan.nextDouble();
                this.scan.nextLine();
                System.out.println("Enter transaction category:\n\tIncome\n\tExpense");
                String category = this.scan.nextLine().toLowerCase();
                if (!(category.equals("income") || category.equals("expense"))) 
                {   System.out.println("Invalid category");   }
                else 
                {   this.addTransaction(new Transaction(transactionAmount, category));   }
            } 
            else if (input == 2) 
            {

            } 
            else if (input == 3) 
            {

            } 
            else if (input == 4) 
            {

            } 
            else if (input == 5) 
            {   System.out.println("Enter how transactions should be sorted:\n\tAmount\n\tDate");
                this.scan.nextLine();
                String sortOption = this.scan.nextLine().toLowerCase();
                if (sortOption.equals("amount"))
                {   Account.sortTransactionsByAmount(this.transactions);
                    System.out.println("Transactions sorted by amount successfully:");
                    for (int i = 0; i < transactions.size(); i++)
                    {   System.out.print(transactions.get(i).getAmount() + " ");   }
                    System.out.print("\n");
                }
            } 
            else if (input == 6) 
            {   System.out.println("Enter how transactions should be sorted:\n\tAmount\n\tDate");
                this.scan.nextLine();
                String sortOption = this.scan.nextLine().toLowerCase();
                if (sortOption.equals("amount"))
                {   Account.sortTransactionsByAmount(this.transactions);
                    Account.reverseTransactionList(this.transactions);
                    System.out.println("Transactions sorted by amount successfully:");
                    for (int i = 0; i < transactions.size(); i++)
                    {   System.out.print(transactions.get(i).getAmount() + " ");   }
                    System.out.print("\n");
                }   
            }
            else if (input == 7) 
            {   flag = false;   }
            else 
            {   System.out.println("Invalid input");   }
        }
    }

    private void setTransactions(ArrayList<Transaction> newTransactions)
    {   this.transactions = newTransactions;   }

    @Override
    public String toString()
    {   String string = "Account number: " + this.accountNumber + "\nDate created: " + this.creationDate;
        if (this.transactions.size() >= 1)
        {   for (int i = 0; i < transactions.size(); i++)
            {   string += transactions.get(i).getAmount();
                string += " ";
            }
            string += "\n";
        }
        return string;
    }
}