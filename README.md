# Know Your Fan

This repository contains the complete solution for the second technical challenge of the FURIA Software Engineering recruitment process.

---

## Backend Overview

* Built with Spring Boot 3.4.5
* Integrates with OpenAI GPT API
* Uses PostgreSQL (Neon) for profile persistence
* Dockerized for local development
* Deployed on Render

**Live Backend:**
[https://know-your-fan-h9ay.onrender.com](https://know-your-fan-h9ay.onrender.com)

**Backend documentation**
[https://documenter.getpostman.com/view/32846088/2sB2j68pji](https://documenter.getpostman.com/view/32846088/2sB2j68pji)

---

## Frontend Overview

* Built with React + Vite
* TailwindCSS for styling
* Axios for API integration
* Hosted on Vercel

**Live Frontend:**
[https://know-your-fan-beta.vercel.app](https://know-your-fan-beta.vercel.app)

---

## How to Run Locally

### 1. Clone the Repository

```bash
git clone https://github.com/luizfsilvano/know-your-fan.git
cd know-your-fan
```

### 2. Backend Setup

```bash
cd backend
./mvnw clean package
java -jar target/*.jar
```

Make sure to configure all required environment variables before running the backend.

### 3. Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Frontend will be available at: [http://localhost:5173](http://localhost:5173)

---

## Environment Variables Required

| Component | Variable                     | Description                                   |
| --------- | ---------------------------- | --------------------------------------------- |
| Backend   | APP\_PROFILE                 | Spring profile (use `dev`)                    |
| Backend   | SPRING\_DATASOURCE\_URL      | Neon PostgreSQL JDBC URL with sslmode=require |
| Backend   | SPRING\_DATASOURCE\_USERNAME | Neon DB user                                  |
| Backend   | SPRING\_DATASOURCE\_PASSWORD | Neon DB password                              |
| Backend   | JWT\_SECRET                  | JWT signing secret                            |
| Backend   | JWT\_EXPIRATION              | Token expiration time in ms                   |
| Backend   | OPENAI\_API\_KEY             | OpenAI GPT API key                            |

---

## Challenges Encountered

* Integrating LangChain4j to structure prompt building and manage OpenAI requests
* Implementing tool calling via OpenAI API to enable data enrichment from external sources
* CORS misconfiguration blocking cross-domain requests from Vercel
* Ensuring JWT-based authentication was correctly integrated between frontend and backend
* Structuring prompts to generate accurate fan profiles with GPT
* Managing secret keys securely during deployment on Render
* Adapting Docker for use without a local database (switching to Neon)

---

## Potential Improvements

* Add form validation and error messages on frontend
* Support multiple languages in the fan profile
* Add admin interface for reviewing user profiles
* Export fan profiles as PDF
* Extend GPT prompt generation with extra context (e.g. Twitch data)
* Add analytics dashboard to track engagement

---

## License

This project is part of a recruitment process and is not intended for commercial use.

---

# Know Your Fan - Português

Este repositório contém a solução completa para o segundo desafio técnico do processo seletivo de Engenharia de Software da FURIA.

---

## Visão Geral do Backend

* Desenvolvido com Spring Boot 3.4.5
* Integração com a API GPT da OpenAI
* Utiliza PostgreSQL (Neon) para persistência de perfis
* Dockerizado para desenvolvimento local
* Implantado na Render

**Backend Online:**
[https://know-your-fan-h9ay.onrender.com](https://know-your-fan-h9ay.onrender.com)

---

## Visão Geral do Frontend

* Desenvolvido com React + Vite
* Estilizado com TailwindCSS
* Integração com a API via Axios
* Hospedado na Vercel

**Frontend Online:**
[https://know-your-fan-beta.vercel.app](https://know-your-fan-beta.vercel.app)

---

## Como Rodar Localmente

### 1. Clonar o Repositório

```bash
git clone https://github.com/luizfsilvano/know-your-fan.git
cd know-your-fan
```

### 2. Configuração do Backend

```bash
cd backend
./mvnw clean package
java -jar target/*.jar
```

Certifique-se de configurar todas as variáveis de ambiente necessárias antes de executar o backend.

### 3. Configuração do Frontend

```bash
cd frontend
npm install
npm run dev
```

O frontend estará disponível em: [http://localhost:5173](http://localhost:5173)

---

## Variáveis de Ambiente Necessárias

| Componente | Variável                     | Descrição                                       |
| ---------- | ---------------------------- | ----------------------------------------------- |
| Backend    | APP\_PROFILE                 | Perfil do Spring (usar `dev`)                   |
| Backend    | SPRING\_DATASOURCE\_URL      | URL JDBC do PostgreSQL Neon com sslmode=require |
| Backend    | SPRING\_DATASOURCE\_USERNAME | Usuário do banco Neon                           |
| Backend    | SPRING\_DATASOURCE\_PASSWORD | Senha do banco Neon                             |
| Backend    | JWT\_SECRET                  | Chave secreta para geração de JWT               |
| Backend    | JWT\_EXPIRATION              | Expiração do token em milissegundos             |
| Backend    | OPENAI\_API\_KEY             | Chave da API GPT da OpenAI                      |

---

## Desafios Encontrados

* Integração com LangChain4j para estruturar a geração de prompts e interações com a OpenAI
* Implementação de tool calling via API da OpenAI para enriquecer respostas com dados externos
* Problemas de CORS bloqueando requisições entre domínios (Vercel e Render)
* Integração segura e funcional da autenticação JWT entre backend e frontend
* Estruturação de prompts para gerar perfis personalizados de fãs
* Gestão segura de variáveis sensíveis durante o deploy na Render
* Adaptação da estrutura Docker para uso com banco externo (Neon)

---

## Melhorias Potenciais

* Adicionar validação de formulário e mensagens de erro no frontend
* Suporte a múltiplos idiomas no perfil de fã
* Criar interface administrativa para revisão de perfis
* Exportar perfis de fã em PDF
* Estender os prompts com contexto adicional (ex: dados da Twitch)
* Adicionar painel analítico para acompanhamento de uso

---

## Licença

Este projeto faz parte de um processo seletivo e não é destinado ao uso comercial.
