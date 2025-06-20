@IzinTerlambat
Feature: Izin Terlambat - Negative Test
  As a logged-in user
  I want to submit late permission without filling the form
  So that I can verify the error validation

  Scenario: Submit empty late permission form
    Given Pengguna sudah login ke aplikasi
    When Pengguna membuka menu izin
    When Pengguna mengakses halaman izin terlambat
    And Klik tombol "Ajukan Izin" tanpa mengisi form
    Then Sistem menampilkan error "Kolom harus diisi"