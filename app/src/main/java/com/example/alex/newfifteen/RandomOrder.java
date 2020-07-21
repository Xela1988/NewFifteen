package com.example.alex.newfifteen;

/**
 * Created by Alex on 22/02/2017.
 */

import java.util.Random;

/*
 * Перемещивает элементы массива в случайном порядке (Agitare per elementi dell'array in ordine casuale)
 * Classe necessaria per la randomizzazione della griglia
 */
public class RandomOrder {
    private static Integer n; //хранит размерность поля (memorizza la dimensione del campo)

    //возвращает перемешанный массив (restituisce un array misto)
    public static Integer[] getShuffleArray(Integer startValue, Integer endValue, Integer dimention) {
        n = dimention;
        Integer mass[] = new Integer[endValue - startValue];
        Integer index = 0;
        for (Integer i = startValue; i < endValue; i++) {
            mass[index++] = i;
        }
        shuffle(mass);
        return mass;
    }

    private static void shuffle(Integer[] arr) {
        Random rnd = new Random();
        for (Integer i = 0; i < arr.length; i++)
            swap(arr, i, rnd.nextInt(arr.length));

        Integer i = 0, j = 1;
        while (!isEvenSum(arr)) {
            swap(arr, i, j);
            i++;
            j++;
        }
    }

    private static void swap(Integer[] arr, Integer i, Integer j) {
        Integer tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //проверка комбинации на разрешимость (Combinazione della prova) isEvenSum
    //è necessario per ssapere se la griglia è risolvibile o meno altrimenti deve essere randomizzata nuovamente
    private static boolean isEvenSum(Integer[] array) {
        Integer countItem = 0;
        Integer rowEmpty = 0, itemValue;
        for (Integer i = 0; i < array.length; i++) {
            itemValue = array[i];
            for (Integer j = i + 1; j < array.length; j++) {
                if ((array[j] != 0) && (array[j] < itemValue))
                    countItem++;
                //кол-во чисел которые меньше числа храняшегося в countItem (Quantità di numeri che sono meno rispetto al numero di hranyashegosya countItem)
            }
            //rowEmpty - строка в которой находится нулевой (пустая ячейка) элемент, (rowEmpty - la linea che è elemento zero (cella vuota),
            //строки нумеруются с единицы (Le stringhe sono numerate da uno)
          /*  if (array[i] == 0)
                rowEmpty = (i - i % n) / n + 1;*/
        }

        //если сумма четная, то решение есть. Иначе комбинация не соберется (se la somma è pari, allora la soluzione è. In caso contrario, la combinazione non incontrerà)
        return countItem % 2 == 0;
    }
}
