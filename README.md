# News App

## Deskripsi
News App adalah aplikasi Android yang menampilkan daftar berita dari **[NewsAPI](https://newsapi.org/)** dengan fitur:
- Melihat daftar berita
- Melihat detail berita
- Menyimpan berita ke bookmark
- Mencari berita berdasarkan kata kunci

Aplikasi ini dibuat dengan fokus pada **Clean Architecture**, state management yang rapi, dan kode yang mudah dipelihara.

---

## Tech Stack
- **Kotlin**
- **Jetpack Compose** (UI modern declarative)
- **Koin** (Dependency Injection)
- **Retrofit** (Networking)
- **Room** (Local Database / Bookmark)
- **Coroutines & Flow** (Asynchronous & reactive data handling)

---

## Arsitektur
Aplikasi menggunakan pendekatan **Clean Architecture** dengan pembagian lapisan:
- **Presentation Layer** → UI + ViewModel (MVVM)
- **Domain Layer** → UseCase (business logic)
- **Data Layer** → Repository, LocalStorage (Room), RemoteDataSource (Retrofit)

Struktur ini memudahkan pemeliharaan, testing, serta pemisahan tanggung jawab tiap lapisan.

---

## Requirement
- **Android Studio**: dianjurkan menggunakan Narwhal Feature Drop | 2025.1.2 Patch 1 (versi lain kemungkinan tetap kompatibel)
- **Gradle**: 8.9
- **minSdk**: 24
- **targetSdk**: 35
- **compileSdk**: 35

Tidak ada kebutuhan khusus seperti Google Services.

---

## Instalasi & Menjalankan Proyek
1. Clone repository:
   ```bash
   git clone https://github.com/BlueShadowBird/News-App.git
   ```

2. Buka project di **Android Studio**.

3. Jalankan aplikasi pada emulator/device.

### API Key
- Proyek menggunakan **NewsAPI**.
- File `assets/api.properties` sudah disertakan dengan API key bawaan (untuk tes).
- Anda juga bisa mendaftarkan API key sendiri di [https://newsapi.org](https://newsapi.org).

---

## Fitur
- **List Berita**: Menampilkan daftar berita terbaru.
- **Detail Berita**: Membuka detail artikel.
- **Bookmark**: Menyimpan artikel untuk dibaca nanti.
- **Search**: Cari artikel dengan kata kunci.

---

## Penilaian yang Difokuskan
- **Clean Architecture** (MVVM, UseCase, Repository, Local/Remote separation)
- **State Management** pada Jetpack Compose
- **Kode yang bersih dan mudah dibaca**

