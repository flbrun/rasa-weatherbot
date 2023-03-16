describe('weatherchatapp', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200')
  })

  it('Send greeting', () => {
    cy.get("input[placeholder=\"Sag etwas...\"]").click().type("Hallo")
    cy.contains('Send').click()
  })

  it('Ask weather location for current time and several citys', () => {
    cy.get("input[placeholder=\"Sag etwas...\"]").type("Wie ist das Wetter in Bielefeld?")
    cy.contains('Send').click()
    cy.get('[id=widget]').should('be.visible')
    cy.get("input[placeholder=\"Sag etwas...\"]").type("Wie ist das Wetter in Stuttgart?")
    cy.contains('Send').click()
    cy.get('[id=widget]').should('be.visible')
  })

  it('Ask weather for several days', () => {
    cy.get("input[placeholder=\"Sag etwas...\"]").type("Wie wird das Wetter morgen in Stuttgart?")
    cy.contains('Send').click()
    cy.get('[id=forecastWidget]').should('be.visible')
  })

  it('Ask weather for several days', () => {
    cy.get("input[placeholder=\"Sag etwas...\"]").type("Wie wird das Wetter übermorgen in Stuttgart?")
    cy.contains('Send').click()
    cy.get('[id=forecastWidget]').should('be.visible')
  })

})
