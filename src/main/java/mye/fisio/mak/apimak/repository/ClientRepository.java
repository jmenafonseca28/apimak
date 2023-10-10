package mye.fisio.mak.apimak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mye.fisio.mak.apimak.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    /* @Query(value = "SELECT " + Config.CLIENTS_COLUMNS.ID + "," +
            Config.CLIENTS_COLUMNS.CEDULA + "," +
            Config.CLIENTS_COLUMNS.ROLE + "," +
            Config.CLIENTS_COLUMNS.NAME + "," +
            Config.CLIENTS_COLUMNS.LAST_NAME + "," +
            Config.CLIENTS_COLUMNS.ENTERPRISE_NAME + "," +
            Config.CLIENTS_COLUMNS.EMAIL + "," +
            Config.CLIENTS_COLUMNS.PASSWORD + "," +
            Config.CLIENTS_COLUMNS.PHONE + " FROM "
            + Config.CLIENTS_TABLE + " c WHERE c." + Config.CLIENTS_COLUMNS.CEDULA + " = ?1", nativeQuery = true)
    public Client findByDocument(String document); */

    public Client findByCedula(String cedula);

    public Client findByEmail(String email);

    public Client findByCedulaOrEmail(String cedula, String email);

    public Client findByPhone(String phone);

}
