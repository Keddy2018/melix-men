package com.api.xmen.services.impl;

import com.api.xmen.services.MutantService;

public class MutantImpl implements MutantService {
    private String dnaLetraPosible = "ATCG";
    private int movimientos = 0;

    @Override
    public boolean isMutant(String[] dna) {
        int coincidenciaDnaMutante = 0;
        if (isDnaValid(dna)) {
            this.movimientos = dna.length - dnaLetraPosible.length();
            char[][] matrizDna = mostrarMatriz(crearMatrizDna(dna));
            for (int i = 0; i < dnaLetraPosible.length(); i++) {
                for (int j = 0; j < dnaLetraPosible.length(); j++) {
                    char[][] matrizMuestra0 = mostrarMatriz(crearMatrizDeComparacion(dnaLetraPosible.toCharArray()[i], j, 'H'));
                    coincidenciaDnaMutante = coincidenciaDnaMutante + buscarMutante(matrizDna, matrizMuestra0);
                    char[][] matrizMuestra1 = mostrarMatriz(crearMatrizDeComparacion(dnaLetraPosible.toCharArray()[i], j, 'V'));
                    coincidenciaDnaMutante = coincidenciaDnaMutante + buscarMutante(matrizDna, matrizMuestra1);
                    if (j == 0) {
                        char[][] matrizMuestra2 = mostrarMatriz(crearMatrizDeComparacion(dnaLetraPosible.toCharArray()[i], j, '\\'));
                        coincidenciaDnaMutante = coincidenciaDnaMutante + buscarMutante(matrizDna, matrizMuestra2);
                        char[][] matrizMuestra3 = mostrarMatriz(crearMatrizDeComparacion(dnaLetraPosible.toCharArray()[i], j, '/'));
                        coincidenciaDnaMutante = coincidenciaDnaMutante + buscarMutante(matrizDna, matrizMuestra3);
                    }
                }
            }
            System.out.println();
            System.out.println("La cantidad de dna MUTANTE encontrada en la secuencia es de:  " + coincidenciaDnaMutante);
        }
        return coincidenciaDnaMutante >= 3 ? true : false ;
    }

    private boolean isDnaValid(String[] dna) {
        for (String sec : dna) {
            int coincidencias = 0;
            for (char letra : sec.toCharArray()) {
                if (this.dnaLetraPosible.contains(String.valueOf(letra))) {
                    coincidencias++;
                }
            }
            if (coincidencias != sec.length()) {
                return false;
            }
        }
        return true;
    }

    private char[][] crearMatrizDna(String[] dna) {
        int n = dna.length;
        char[][] matrizDna = new char[n][n];
        int contadorSec = 0;
        for (String sec : dna) {
            int contadorLet = 0;
            for (char letra : sec.toCharArray()) {
                matrizDna[contadorSec][contadorLet] = letra;
                contadorLet++;
            }
            contadorSec++;
        }
        return matrizDna;
    }

    private char[][] crearMatrizDeComparacion(char letra, int posicion, char direccion) {
        int n = dnaLetraPosible.length();
        char[][] matrizComparacion = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == posicion && direccion == 'H') {
                    matrizComparacion[i][j] = letra;
                } else if (j == posicion && direccion == 'V') {
                    matrizComparacion[i][j] = letra;
                } else if (j == posicion + i && i == posicion + j && direccion == '\\') {
                    matrizComparacion[i][j] = letra;
                } else if (i == n - 1 - j && j == n - 1 - i && direccion == '/') {
                    matrizComparacion[i][j] = letra;
                } else {
                    matrizComparacion[i][j] = 'O';
                }
            }
        }
        return matrizComparacion;
    }

    private char[][] mostrarMatriz(char[][] matriz) {
        System.out.println("");
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("");
            for (int j = 0; j < matriz.length; j++) {
                System.out.print("  " + matriz[i][j] + "  ");
            }
        }
        return matriz;
    }

    private int[] moverMatriz(int[] cordenadas){
        int[] cordenadasNuevas = {0,0};
        if(cordenadas != cordenadasNuevas){
                if(cordenadas[0] < this.movimientos){  //X = 0  1  2   Y=
                    cordenadas[0]++;
                }else{
                    cordenadas[1]++;
                    cordenadas[0] = 0;
                }
                return cordenadas;
        }
        return cordenadasNuevas;
    }

    private int buscarMutante(char[][] matrizDna, char[][] matrizMuestra) {
        int coincidenciaPosibleMutante = 0;
        int movimientoDeMatriz = matrizDna.length - matrizMuestra.length;
        int[] cordenadas = {0,0};
        while(cordenadas[1] <= this.movimientos){
            int coincidenciasLetraDna = 0;
            for (int y = 0; y < matrizMuestra.length; y++) {
                for (int x = 0; x < matrizMuestra.length; x++) {
                    if (matrizDna[x + cordenadas[0]][y + cordenadas[1]] == matrizMuestra[x][y]) {
                        coincidenciasLetraDna++;
                    }
                }
                if (coincidenciasLetraDna == matrizMuestra.length) {
                    System.out.println("    -->  ENCONTRE UN POSIBLE ADN " + coincidenciasLetraDna  +  "   en las cordenadas : " + "X: " + cordenadas[0] + "  Y: " + cordenadas[1]);
                    coincidenciaPosibleMutante++;
                }
            }
            cordenadas = moverMatriz(cordenadas);
        }
        return coincidenciaPosibleMutante;
    }
}
