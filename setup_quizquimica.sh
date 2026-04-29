#!/bin/bash

echo "🔬 Criando estrutura do QuizQuimica..."

# Raiz
mkdir -p QuizQuimica
cd QuizQuimica

# .gitignore
cat > .gitignore << 'EOF'
# NetBeans / VS Code
nbproject/private/
.vscode/
build/
dist/
nbbuild/
nbdist/

# Compilados Java
*.class
*.jar

# Banco SQLite
*.db
*.sqlite

# Sistema operacional
.DS_Store
Thumbs.db

# Logs
*.log

# Config sensível
config.properties
EOF

# resources
mkdir -p resources
touch resources/banco.db
touch resources/config.properties

# Pacote model
mkdir -p src/quizquimica/model
touch src/quizquimica/model/Usuario.java
touch src/quizquimica/model/Aluno.java
touch src/quizquimica/model/Professor.java
touch src/quizquimica/model/Questao.java
touch src/quizquimica/model/Alternativa.java
touch src/quizquimica/model/Dica.java
touch src/quizquimica/model/Nivel.java
touch src/quizquimica/model/Quiz.java
touch src/quizquimica/model/Sala.java
touch src/quizquimica/model/Partida.java
touch src/quizquimica/model/Desempenho.java

# Pacote dao
mkdir -p src/quizquimica/dao
touch src/quizquimica/dao/UsuarioDAO.java
touch src/quizquimica/dao/AlunoDAO.java
touch src/quizquimica/dao/ProfessorDAO.java
touch src/quizquimica/dao/QuestaoDAO.java
touch src/quizquimica/dao/QuizDAO.java
touch src/quizquimica/dao/SalaDAO.java
touch src/quizquimica/dao/DesempenhoDAO.java

# Pacote service
mkdir -p src/quizquimica/service
touch src/quizquimica/service/AuthService.java
touch src/quizquimica/service/QuizService.java
touch src/quizquimica/service/QuestaoService.java
touch src/quizquimica/service/SalaService.java
touch src/quizquimica/service/DesempenhoService.java

# Pacote controller
mkdir -p src/quizquimica/controller
touch src/quizquimica/controller/AuthController.java
touch src/quizquimica/controller/AlunoController.java
touch src/quizquimica/controller/ProfessorController.java

# Pacote view
mkdir -p src/quizquimica/view/auth
touch src/quizquimica/view/auth/TelaLogin.java

mkdir -p src/quizquimica/view/aluno
touch src/quizquimica/view/aluno/PainelAluno.java
touch src/quizquimica/view/aluno/TelaQuiz.java
touch src/quizquimica/view/aluno/TelaDesempenho.java

mkdir -p src/quizquimica/view/professor
touch src/quizquimica/view/professor/PainelProfessor.java
touch src/quizquimica/view/professor/TelaGerenciarQuestoes.java
touch src/quizquimica/view/professor/TelaGerenciarQuiz.java
touch src/quizquimica/view/professor/TelaGerenciarSala.java
touch src/quizquimica/view/professor/TelaRelatorioAlunos.java

# Pacote util
mkdir -p src/quizquimica/util
touch src/quizquimica/util/Constantes.java
touch src/quizquimica/util/ValidadorEmail.java
touch src/quizquimica/util/GeradorSenha.java
touch src/quizquimica/util/ConexaoDB.java

# Main
touch src/quizquimica/Main.java

echo ""
echo "✅ Estrutura criada com sucesso!"
echo ""
echo "📁 Estrutura gerada:"
find . -not -path './.git/*' | sort | sed 's|[^/]*/|  |g'
echo ""
echo "👉 Próximos passos:"
echo "   1. Copie a pasta QuizQuimica para dentro do seu repositório"
echo "   2. git add ."
echo "   3. git commit -m 'chore: estrutura inicial do projeto'"
echo "   4. git push"
