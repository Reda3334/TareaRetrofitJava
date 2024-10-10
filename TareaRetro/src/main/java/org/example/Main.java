package org.example;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String usuario = s.nextLine();

        while (true) {
            System.out.print("Introduce el nombre de usuario: ");
            String user = s.nextLine();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);

            Call<List<Repo>> repos = service.listRepos(user);
            List<Repo> repoList = repos.execute().body();
            for (Repo repositorio : repoList){
                if (repositorio.description != null){
                    System.out.println( "nombre: " + repositorio.name + " / " + " descripción: " + repositorio.description + " / " +  " followers: " +repositorio.stargazers_count);
                }
                else {
                    System.out.println( "nombre: " + repositorio.name + " / " +  " descripción: No hay descripción "  + " / " +  " followers: " +repositorio.stargazers_count);
                }
            }
        }
    }
}