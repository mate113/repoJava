package model;

import exception.PublicationAlreadyExistsException;
import exception.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {
    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Optional<Publication> findPublicationByTitle(String title){
        return Optional.of(publications.get(title));
    }

    public void addPublication(Publication publication){
        if(publications.containsKey(publication.getTitle())) {
            throw new PublicationAlreadyExistsException("Publikacja \"" + publication.getTitle() + "\" już istnieje.");
        }
        publications.put(publication.getTitle(), publication);
    }

    public void addUser(LibraryUser user){
        if(publications.containsKey(user.getPesel())) {
            throw new UserAlreadyExistsException("Użytkownik z peselem" + user.getPesel() + " już istnieje.");
        }
        users.put(user.getPesel(), user);
    }

    public boolean removePublication(Publication publication){
        boolean found = false;
        if(publications.containsKey(publication.getTitle())) found = true;
        if(found){
            publications.remove(publication.getTitle());
        }
        return found;
    }

    public Map<String, Publication> getPublications(){
        return publications;
    }

    public Map<String, LibraryUser> getUsers(){
        return users;
    }

    public Collection<Publication> getSortedPublications(Comparator<Publication> comparator){
        ArrayList<Publication> publicationList = new ArrayList<>(publications.values());
        publicationList.sort(comparator);
        return publicationList;
    }

    public Collection<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator){
        ArrayList<LibraryUser> usersList = new ArrayList<>(users.values());
        usersList.sort(comparator);
        return usersList;
    }
}
