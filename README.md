# 📝 NoteyApp - Guia de Estudo Android

Este projeto é uma aplicação de notas simples desenvolvida para consolidar conhecimentos em **Jetpack Compose**, **Room Database** e arquitetura **MVVM** (Model-View-ViewModel).

## 🚀 Tecnologias Utilizadas

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **UI:** [Jetpack Compose](https://developer.android.com/compose) (Arquitetura Declarativa)
- **Base de Dados:** [Room](https://developer.android.com/training/data-storage/room) (Abstração sobre SQLite)
- **Gestão de Estado:** ViewModel & LiveData
- **Injeção de Dependências:** Factory Pattern (NoteViewModelFactory)
- **Async:** Kotlin Coroutines

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de recomendação da Google para o desenvolvimento Android moderno:

1.  **UI Layer (Compose):** Localizada no pacote `screens`, responsável por exibir os dados e capturar interações do utilizador.
2.  **ViewModel:** Atua como ponte entre a UI e o Repositório, sobrevivendo a mudanças de configuração (como rodar o ecrã).
3.  **Repository:** Centraliza o acesso aos dados, decidindo se os dados vêm da base de dados local ou (futuramente) de uma API.
4.  **Data Layer (Room):**
    - `Note`: A entidade (tabela no SQL).
    - `NoteDao`: Interface com as queries (Insert, Query).
    - `NotesDB`: A base de dados principal (Singleton).

---

## 📚 Conceitos Chave Aprendidos

### 1. Padrão Singleton na Base de Dados
Para evitar que a aplicação crie múltiplas instâncias da base de dados (o que consome muita memória), utilizamos o padrão **Singleton** no ficheiro `NotesDB.kt`.
> **Dica de Estudo:** O uso de `@Volatile` garante que o valor da instância esteja sempre atualizado para todas as threads.

### 2. Ciclo de Vida e Duplicação de Dados
Um erro comum é inserir dados diretamente no `onCreate` da `MainActivity`. 
- **Problema:** Cada vez que o ecrã roda, a `Activity` é recriada e os dados são inseridos novamente.
- **Solução:** Inserir dados apenas através de ações do utilizador (clique num botão) ou verificar se os dados já existem.

### 3. Jetpack Compose: Dicas de Layout
- **Scaffold:** Utiliza sempre o `innerPadding` fornecido pelo `Scaffold` no teu conteúdo principal. Caso contrário, a UI poderá ficar por baixo da Status Bar ou da Bottom Bar.
- **LazyVerticalStaggeredGrid:** Excelente para layouts estilo "Pinterest". Nota: Cuidado com os imports de `items`, deve ser o específico para `staggeredgrid`.

---

## 📂 Estrutura de Ficheiros Relevantes

- `MainActivity.kt`: Ponto de entrada e configuração do ViewModel.
- `NotesDB.kt`: Configuração do Room.
- `DisplayDialog.kt`: UI para criação de novas notas.
- `MyColorPicker.kt`: Componente personalizado para seleção de cores (estudo de `LazyRow`).

---

## 📝 Como testar
1. Clona o repositório.
2. Faz o *Sync* do Gradle.
3. Executa num emulador ou dispositivo físico.
4. Clica no botão **+** (FAB) para adicionar uma nota e escolhe uma cor.

---

<img width="454" height="935" alt="Captura de ecrã 2026-04-15 105907" src="https://github.com/user-attachments/assets/b1bd6903-9b79-4eee-849d-fe8387398d5a" />
<img width="450" height="932" alt="Captura de ecrã 2026-04-15 105847" src="https://github.com/user-attachments/assets/1601e3ac-a942-44e3-bfa1-61153d3d2d93" />

