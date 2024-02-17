package packModele;

/**
 * La classe Etudiant représente un étudiant avec des informations telles que le
 * numéro, le nom, le prénom,
 * le type de baccalauréat et le département.
 * Elle permet de stocker et de gérer ces informations.
 * 
 * @author Yanis Mechta
 */
public class Etudiant {
    /**
     * Le numéro d'étudiant.
     */
    private String numero;

    /**
     * Le nom de l'étudiant.
     */
    private String nom;

    /**
     * Le prénom de l'étudiant.
     */
    private String prenom;

    /**
     * Le type de baccalauréat de l'étudiant.
     */
    private String bac;

    /**
     * Le département de l'étudiant.
     */
    private String dept;

    /**
     * Constructeur de la classe Etudiant.
     * 
     * @param numero Le numéro d'étudiant.
     * @param nom    Le nom de l'étudiant.
     * @param prenom Le prénom de l'étudiant.
     * @param bac    Le type de baccalauréat de l'étudiant.
     * @param dept   Le département de l'étudiant.
     */
    public Etudiant(String numero, String nom, String prenom, String bac, String dept) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.bac = bac;
        this.dept = dept;
    }

    /**
     * @return String Le numéro d'étudiant.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero Le numéro d'étudiant à définir.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return String Le nom de l'étudiant.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom Le nom de l'étudiant à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return String Le prénom de l'étudiant.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom Le prénom de l'étudiant à définir.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return String Le type de baccalauréat de l'étudiant.
     */
    public String getBac() {
        return bac;
    }

    /**
     * @param bac Le type de baccalauréat de l'étudiant à définir.
     */
    public void setBac(String bac) {
        this.bac = bac;
    }

    /**
     * @return String Le département de l'étudiant.
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept Le département de l'étudiant à définir.
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations de
     * l'étudiant.
     * 
     * @return String Une représentation sous forme de chaîne de caractères de
     *         l'étudiant.
     */
    @Override
    public String toString() {
        return numero + " - " + nom + " " + prenom + " (" + dept + ")";
    }
}
