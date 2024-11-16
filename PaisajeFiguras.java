import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class PaisajeFiguras extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fondo
        g2d.setColor(new Color(173, 216, 230)); // Color cielo
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Pasto
        g2d.setColor(new Color(34, 139, 34)); // Verde
        g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Sol
        drawSun(g2d);

        // Casa
        drawHouse(g2d);

        // Árboles
        drawTree(g2d, 150, 250);
        drawTree(g2d, 200, 250);

        // Personas
        drawPerson(g2d, 100, 350, 1.0);   // Persona grande
        drawPerson(g2d, 250, 350, 0.75); // Persona mediana
        drawPerson(g2d, 400, 350, 0.5);  // Persona pequeña
    }

    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        int centerX = 90, centerY = 90, radius = 40, numRays = 12, rayLength = 20;

        for (int i = 0; i < numRays; i++) {
            double angle = 2 * Math.PI / numRays * i;
            int x1 = centerX + (int) ((radius + rayLength) * Math.cos(angle));
            int y1 = centerY + (int) ((radius + rayLength) * Math.sin(angle));
            int x2 = centerX + (int) (radius * Math.cos(angle - Math.PI / numRays));
            int y2 = centerY + (int) (radius * Math.sin(angle - Math.PI / numRays));
            int x3 = centerX + (int) (radius * Math.cos(angle + Math.PI / numRays));
            int y3 = centerY + (int) (radius * Math.sin(angle + Math.PI / numRays));

            g2d.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        }

        g2d.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    private void drawHouse(Graphics2D g2d) {
        g2d.setColor(new Color(255, 165, 0));
        g2d.fillRect(300, 200, 150, 150);
        g2d.setColor(Color.RED);
        g2d.fillPolygon(new int[]{275, 475, 375}, new int[]{200, 200, 130}, 3);

        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(330, 275, 30, 75);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(375, 225, 50, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawLine(400, 225, 400, 275);
        g2d.drawLine(375, 250, 425, 250);
    }

    private void drawTree(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(x + 10, y + 50, 20, 50);

        g2d.setColor(Color.GREEN);
        g2d.fillPolygon(new int[]{x, x + 40, x + 20}, new int[]{y + 50, y + 50, y}, 3);
    }

    private void drawPerson(Graphics2D g2d, int x, int y, double scale) {
        AffineTransform original = g2d.getTransform();

        // Escalar y posicionar
        g2d.translate(x, y);
        g2d.scale(scale, scale);

        // Cabeza
        g2d.setColor(Color.PINK);
        g2d.fillOval(-20, -60, 40, 40);

        // Caras felices dentro del círculo
        g2d.setColor(Color.BLACK);
        g2d.fillOval(-10, -50, 5, 5); // Ojo izquierdo
        g2d.fillOval(5, -50, 5, 5);  // Ojo derecho
        g2d.drawArc(-10, -40, 20, 10, 180, 180); // Sonrisa

        // Brazos
        g2d.setColor(Color.PINK);
        g2d.fillRect(-35, -10, 70, 10);

        // Cuerpo
        g2d.setColor(Color.BLUE);
        g2d.fillRect(-15, -20, 30, 50);

        // Piernas
        g2d.setColor(Color.BLUE);
        g2d.fillRect(-10, 30, 10, 30);
        g2d.fillRect(0, 30, 10, 30);

        g2d.setTransform(original);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Paisaje con Figuras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new PaisajeFiguras());
        frame.setVisible(true);
    }
}
