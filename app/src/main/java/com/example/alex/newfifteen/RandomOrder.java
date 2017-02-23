package com.example.alex.newfifteen;

/**
 * Created by Alex on 22/02/2017.
 */
import java.util.Random;

/*
* Перемещивает элементы массива в случайном порядке (Agitare per elementi dell'array in ordine casuale)
*/
public class RandomOrder {
    private static int n; //хранит размерность поля (memorizza la dimensione del campo)

    //возвращает перемешанный массив (restituisce un array misto)
    public static int[] getShuffleArray(int startValue, int endValue, int dimention) {
        n = dimention;
        int mass[] = new int[endValue - startValue];
        int index = 0;
        for (int i = startValue; i < endValue; i++) {
            mass[index++] = i;
        }
        shuffle(mass);
        return mass;
    }

    private static void shuffle(int[] arr) {
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++)
            swap(arr, i, rnd.nextInt(arr.length));

        int i = 0, j = 1;
        while (!isEvenSum(arr)) {
            swap(arr, i, j);
            i++; j++;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //проверка комбинации на разрешимость (Combinazione della prova )
    private static boolean isEvenSum(int[] array) {
        int countItem = 0;
        int rowEmpty = 0, itemValue;
        for (int i = 0; i < array.length; i++) {
            itemValue = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if ((array[j] != 0) && (array[j] < itemValue)) countItem++;
                //кол-во чисел которые меньше числа храняшегося в countItem (Quantità di numeri che sono meno rispetto al numero di hranyashegosya countItem)
            }
            //rowEmpty - строка в которой находится нулевой (пустая ячейка) элемент, (rowEmpty - la linea che è elemento zero (cella vuota),
            //строки нумеруются с единицы (Le stringhe sono numerate da uno)
            if (array[i] == 0) rowEmpty = (i - i % n) / n + 1;
        }

        //если сумма четная, то решение есть. Иначе комбинация не соберется (se la somma è pari, allora la soluzione è. In caso contrario, la combinazione non incontrerà)
        return (countItem + rowEmpty) % 2 == 0;
    }
}
