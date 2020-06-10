package BasicGraph;

import java.util.ArrayList;

public class Matrix2D<V> {
        private int used= 0;

        private ArrayList<ArrayList<V> > data;

        public Matrix2D()
        {
            data= new ArrayList<ArrayList<V> >();
        }


        public int cantElements()
        {
            return used;
        }

        private boolean checkLimits(int row, int col) 	{
            return checkLimits(row) && checkLimits(col);
        }

        private boolean checkLimits(int pos) 	{
            return pos < cantElements();
        }

        public V get(int row, int col)
        {
            if (! checkLimits(row, col))
                throw new IndexOutOfBoundsException();

            return data.get(row).get(col);
        }

        public void set(int row, int col, V value)
        {
            if (! checkLimits(row, col))
                throw new IndexOutOfBoundsException();

            data.get(row).set(col, value);

        }

        public void append(V value) {
            used++;

            int oldSize= data.size();
            data.add(new ArrayList<V>() );
            for (int i = 0; i < oldSize; i++) {
                data.get(oldSize).add(value);
            }

            // data size fue incrementado con el add
            for (int i = 0; i < data.size(); i++) {
                data.get(i).add(value);
            }

        }

        public void fillCol(int col, V value)
        {
            if (! checkLimits(col))
                throw new IndexOutOfBoundsException();

            for (int i = 0; i < cantElements(); i++) {
                set(i, col, value);
            }
        }

        public void fillRow(int row, V value)
        {
            if (! checkLimits(row))
                throw new IndexOutOfBoundsException();

            for (int i = 0; i < cantElements(); i++) {
                set(row, i, value);
            }
        }

        // remove
        public void remove(int pos)
        {
            if (! checkLimits(pos) || cantElements() == 0)
                throw new IndexOutOfBoundsException();

            used--;

            data.remove(pos );

            for (int i = 0; i < cantElements(); i++) {
                data.get(i).remove(pos);
            }

            data.trimToSize();
        }

        public void dump()
        {
            for (ArrayList<V> outer : data) {
                for (V inner : outer) {
                    System.out.print(String.format("%6s", inner));
                }
                System.out.println("\n");
            }
        }

        public static void main(String[] args) {
            Matrix2D<Integer> rta = new Matrix2D<Integer>();
            System.out.println(rta.cantElements());
            rta.dump();


            // error
            //	rta.fillCol(0, 550);

            //
            rta.append(73);
            System.out.println(rta.cantElements());
            rta.dump();

            rta.append(23);
            System.out.println(rta.cantElements());
            rta.dump();


            rta.append(35);
            System.out.println(rta.cantElements());
            rta.dump();


            rta.append(18);
            System.out.println(rta.cantElements());
            rta.dump();


            rta.append(13);
            System.out.println(rta.cantElements());
            rta.dump();


            rta.append(18);
            System.out.println(rta.cantElements());
            rta.dump();

            // error
//		rta.set(1, 2, 111111);

            rta.set(0,  0,  2222);
            rta.set(1,  0,  555);
            System.out.println(rta.cantElements());
            rta.dump();

            rta.append(4);
            System.out.println(rta.cantElements());
            rta.dump();

            //
            rta.remove(0);
            System.out.println(rta.cantElements());
            rta.dump();

            rta.remove(5);
            System.out.println(rta.cantElements());
            rta.dump();

            rta.remove(1);
            System.out.println(rta.cantElements());
            rta.dump();

            rta.append(15);
            System.out.println(rta.cantElements());
            rta.dump();

            rta.append(22);
            System.out.println(rta.cantElements());
            rta.dump();

        }
}
