public class Main {
    //Assume that verified business numbers are stored in this array and is updated beforehand
    public static String[] verifiedBusinessNumbers;
    public static EntertainmentProvider[] businesses;
    public static int businessCount = 0;

    //Build max heap
    //Heap sort is chosen as in a future case where more verified business numbers are added -
    //An insert heap method can be made with O(log n) time complexity to quickly add new business numbers to the array while keeping it sorted
    private static void heapify(String[] arr, int n, int i){
        int largest = i;
        //Left and right child indices
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        //Left/right child is larger than root
        if(l < n && arr[l].compareTo(arr[largest]) > 0) {
            largest = l;
        }
        if(r < n && arr[r].compareTo(arr[largest]) > 0) {
            largest = r;
        }
        //Recursive call if largest is not root
        if(largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    private static void heapSort(){
        int n = verifiedBusinessNumbers.length;
        
        //Heapify non root nodes, build max heap
        for(int i = n / 2 - 1; i >= 0; i--) {
            heapify(verifiedBusinessNumbers, n, i);
        }
        
        //Heapify root nodes and swap with last element till sorted
        for(int i = n - 1; i > 0; i--) {
            String temp = verifiedBusinessNumbers[0];
            verifiedBusinessNumbers[0] = verifiedBusinessNumbers[i];
            verifiedBusinessNumbers[i] = temp;
            heapify(verifiedBusinessNumbers, i, 0);
        }
    }

    //Binary search the sorted array to check for the valid buisness number
    //Time complexity of O(log n), fast for millions of business numbers
    private static boolean isValidBusinessNumber(String number) {
        int l = 0;
        int r = verifiedBusinessNumbers.length - 1;

        while(l <= r) {
            int m = l + (r - l) / 2;
            if(verifiedBusinessNumbers[m].equals(number)) {
                return true;
            }
            else if(verifiedBusinessNumbers[m].compareTo(number) < 0) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return false;
    }

    //Store EP's register data using this class
    //Class is implemented inside the code for simplicity, but will be made into a separate file in the full implementation
    public class EntertainmentProvider {
        String organisationName;
        String businessNumber;
        String mainContactEmail;
        String description;

        public EntertainmentProvider(String organisationName, String businessNumber, String mainContactEmail, String description) {
            this.organisationName = organisationName;
            this.businessNumber = businessNumber;
            this.mainContactEmail = mainContactEmail;
            this.description = description;
        }
    }

    private static boolean isValidEmail(String email){
        //A rather simple email validation system that checks if "@" and "." are present with no spaces
        if(email.contains("@") && email.contains(".") && !email.contains(" ")) {
            return true;
        }
        return false;
    }

    public static boolean isValidBusiness(EntertainmentProvider business) {
        if(business.organisationName != null && business.businessNumber != null && business.mainContactEmail != null && business.description != null) {
            if(isValidBusinessNumber(business.businessNumber) && isValidEmail(business.mainContactEmail)) {
                return true;
            }
        }
        return false;
    }

    //To run the program assume that verified business numbers are stored in the array beforehand 
    //Heapsort the verified business numbers, take input for EntertainmentProvider
    // Use isValidBusiness() to check if the business is valid and print the result

}
