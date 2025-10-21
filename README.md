# 🔐 Simple Auth - Sistema de Login e Cadastro de Usuários

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Build-Maven-orange?logo=apachemaven)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/Tests-JUnit5-green?logo=junit5)](https://junit.org/junit5/)
[![CI/CD](https://github.com/WilliamD2022/logintest/actions/workflows/ci.yml/badge.svg)](https://github.com/WilliamD2022/logintest/actions)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](LICENSE)

---

## 📘 Sobre o Projeto

Aplicação **Java Spring Boot** simples e modular, desenvolvida para demonstrar:
- Autenticação e cadastro de usuários.
- Testes automatizados com **JUnit e Mockito**.
- Integração e entrega contínua (**CI/CD**) com GitHub Actions.
- Empacotamento automático em `.jar` e deploy (simulado ou real na AWS EC2).

---

## 🧩 Estrutura do Projeto

logintest/
├── com.login.william/
│ ├── src/
│ │ ├── main/java/com/login/william/
│ │ │ ├── AuthApplication.java
│ │ │ ├── config/SecurityConfig.java
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ ├── repository/
│ │ │ ├── dto/
│ │ │ └── model/
│ │ └── test/java/com/login/william/
│ └── pom.xml
├── .github/workflows/
│ ├── ci.yml
│ └── cd-ec2.yml
└── README.md


---

## 🚀 Como Executar Localmente

### 🧰 Pré-requisitos
- **Java 17**
- **Maven 3.9+**
- **Git**

### 🧩 Passos

```bash
# Clonar o repositório
git clone https://github.com/WilliamD2022/logintest.git
cd logintest/com.login.william

# Compilar e rodar testes
mvn clean test

# Empacotar o projeto
mvn clean package

# Executar a aplicação
java -jar target/simple-auth-1.0.0.jar

➡️ Acesse: http://localhost:8080

➡️ Console H2: http://localhost:8080/h2


JDBC URL: jdbc:h2:mem:authdb | User: sa | Senha: (vazia)
🧪 Testes Automatizados

Executa todos os testes unitários via JUnit 5 e Mockito:

mvn test

Relatórios são gerados em:

com.login.william/target/surefire-reports/

⚙️ CI/CD com GitHub Actions
🧱 CI — Integração Contínua (.github/workflows/ci.yml)

    Compila o projeto

    Executa testes unitários

    Gera o .jar final

    Publica artefatos:

        simple-auth.jar

        surefire-reports

🚀 CD — Entrega Contínua (.github/workflows/cd-ec2.yml)

    Baixa artefato gerado pelo CI

    Simula deploy local (sem AWS)

    Logs detalhados via ACTIONS_STEP_DEBUG

    (Opcional) Deploy real via SSH para EC2 AWS

☁️ Como Configurar Deploy Real na AWS

    Acesse Settings → Secrets and variables → Actions → New repository secret

    Crie os seguintes secrets:

Nome	Descrição
EC2_HOST	IP público ou DNS da instância
EC2_USER	Usuário SSH (ex: ec2-user, ubuntu)
EC2_SSH_KEY	Conteúdo da chave privada .pem
EC2_PORT	Porta SSH (opcional, padrão: 22)

    No arquivo cd-ec2.yml, altere:

SIMULATE_DEPLOY: false

    Na sua EC2, crie o script /opt/simple-auth/deploy.sh:

#!/bin/bash
cd /opt/simple-auth
echo "Deploy iniciado em $(date)"
java -jar /tmp/simple-auth.jar &

    Dê permissão:

sudo chmod +x /opt/simple-auth/deploy.sh

📦 Artefatos Gerados
Nome	Descrição
simple-auth-jar	Aplicação empacotada para deploy
surefire-reports	Relatórios de testes JUnit
🧠 Tecnologias
Categoria	Tecnologia
Linguagem	Java 17
Framework	Spring Boot 3.3.4
Build	Maven
Banco	H2 Database (in-memory)
Testes	JUnit 5, Mockito
CI/CD	GitHub Actions
Deploy	SSH / AWS EC2
Segurança	Spring Security (Basic Auth)
👨‍💻 Autor

William Domingues Barbosa
Engenheiro de Software | QA | DevOps | AWS | Java | GoLang
📍 Atibaia - SP
