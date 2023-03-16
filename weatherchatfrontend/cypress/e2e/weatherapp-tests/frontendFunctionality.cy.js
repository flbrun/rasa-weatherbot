describe('weatherchatapp', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200')
  })

  it('Check if Backend is running', () => {
    cy.contains('stell mir eine Frage').should('be.visible')
  })

  it('Send empty message with send button', () => {
    cy.contains('Send').should('be.disabled')
  })

  it('Send empty message with enter', () => {
    cy.contains('Send').should('be.disabled')
  })

  it('Send message with spaces via send button', () => {
    cy.get("input[placeholder=\"Sag etwas...\"]").type("   ")
    cy.contains('Send').click()
    .should('be.disabled')
  })

  it('Send message with spaces via enter', () => {
    cy.get("input[placeholder=\"Sag etwas...\"]").type("   ").type('Cypress.io{enter}')
    .should('be.disabled')
  })

  it('Severale user messages without bot answer', () => {
    cy.get("input[placeholder=\"Sag etwas...\"]").type("Hallo")
    cy.contains("Send").click()
    cy.get("input[placeholder=\"Sag etwas...\"]").should('be.disabled')
    cy.get("input[placeholder=\"Sag etwas...\"]").type("Hallo")
    cy.contains("Send").click()
  })

  it('Check if messages from user are visible', () => {
    cy.get("input[placeholder=\"Sag etwas...\"]").type("Hallo")
    cy.contains("Send").click()
    cy.get("[id=textBubble]").contains("Hallo").should('be.visible')
  })

})

