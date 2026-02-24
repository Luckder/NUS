class Quick:

    def __init__(self):
        self.count = 0

        print("""\n
  /$$$$$$  /$$   /$$ /$$$$$$  /$$$$$$  /$$   /$$  /$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$$$
 /$$__  $$| $$  | $$|_  $$_/ /$$__  $$| $$  /$$/ /$$__  $$ /$$__  $$| $$__  $$|__  $$__/
| $$  \ $$| $$  | $$  | $$  | $$  \__/| $$ /$$/ | $$  \__/| $$  \ $$| $$  \ $$   | $$   
| $$  | $$| $$  | $$  | $$  | $$      | $$$$$/  |  $$$$$$ | $$  | $$| $$$$$$$/   | $$   
| $$  | $$| $$  | $$  | $$  | $$      | $$  $$   \____  $$| $$  | $$| $$__  $$   | $$   
| $$/$$ $$| $$  | $$  | $$  | $$    $$| $$\  $$  /$$  \ $$| $$  | $$| $$  \ $$   | $$   
|  $$$$$$/|  $$$$$$/ /$$$$$$|  $$$$$$/| $$ \  $$|  $$$$$$/|  $$$$$$/| $$  | $$   | $$   
 \____ $$$ \______/ |______/ \______/ |__/  \__/ \______/  \______/ |__/  |__/   |__/   
      \__/                                                                             \n 
""")

    def sort(self, arr):
        self.count = 0

        result = self.quicksort(arr)

        return result

    def partition(self, array, low, high):
        pivot = array[high]
        i = low - 1

        for j in range(low, high):
            if array[j][0] <= pivot[0]:
                i += 1
                array[i], array[j] = array[j], array[i]

                self.count += 1

        array[i+1], array[high] = array[high], array[i+1]
        self.count += 1

        return i + 1

    def quicksort(self, array, low=0, high=None):
        if high is None:
            high = len(array) - 1

        if low < high:
            pivot_index = self.partition(array, low, high)
            self.quicksort(array, low, pivot_index - 1)
            self.quicksort(array, pivot_index + 1, high)

        return array